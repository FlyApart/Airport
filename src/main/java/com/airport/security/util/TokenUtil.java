package com.airport.security.util;

import com.airport.config.web.JwtConfiguration;
import com.airport.util.ProjectDate;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.jsonwebtoken.Claims.SUBJECT;

@Component
@RequiredArgsConstructor
public class TokenUtil {

	private final JwtConfiguration jwtConfiguration;

	private final ProjectDate projectDate;

	private static final String CREATED_VALUE = "created";
	private static final String ROLE = "role";

	public String getUserFromToken (String token) {
		return getClaimsFromToken (token).getSubject ();
	}

	public Date getCreatedDateFromToken (String token) {
		return (Date) getClaimsFromToken (token).get (CREATED_VALUE);
	}

	public Date getExpirationDateFromToken (String token) {
		return getClaimsFromToken (token).getExpiration ();
	}

	private Claims getClaimsFromToken (String token) {
		return Jwts.parser ()
		           .setSigningKey (jwtConfiguration.getSecret ())
		           .parseClaimsJwt (token)
		           .getBody ();

	}

	private Boolean isTokenExpired (String token) {
		final Date expiration = this.getExpirationDateFromToken (token);
		return expiration.before (projectDate.getCurrentTime ());
	}

	private Boolean isCreatedBeforeLastPasswordReset (Date created, Date lastPasswordReset) {
		return (lastPasswordReset != null && created.before (lastPasswordReset));
	}


	private String generateToken (Map<String, Object> claims) {
		return Jwts.builder ()//TODO add header
		           .setClaims (claims)
		           .setIssuedAt (new Date (System.currentTimeMillis ()))
		           .setExpiration (projectDate.generateTokenExpirationDate (jwtConfiguration.getExpire ()))
		           .signWith (SignatureAlgorithm.HS512, jwtConfiguration.getSecret ())
		           .compact ();
	}

	public String generateToken (UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<> ();
		claims.put (SUBJECT, userDetails.getUsername ());
		claims.put (CREATED_VALUE, projectDate.getCurrentTime ());
		claims.put (ROLE, getEncryptedRole (userDetails));
		return generateToken (claims);
	}

	private List<String> getEncryptedRole (UserDetails userDetails) {
		return userDetails.getAuthorities ()
		                  .stream ()
		                  .map (GrantedAuthority::getAuthority)
		                  .map (s -> s.replace ("ROLE_", ""))
		                  .map (String::toLowerCase)
		                  .collect (Collectors.toList ());
	}


	public Boolean canTokenBeRefreshed (String token, Date lastPasswordReset) {
		final Date created = this.getCreatedDateFromToken (token);
		return (!this.isCreatedBeforeLastPasswordReset (created, lastPasswordReset) && (!this.isTokenExpired (token)) /*|| this.ignoreTokenExpiration (token)*/);
	}

	public String refreshToken (String token) {
		String refreshToken;
		try {
			final Claims claims = this.getClaimsFromToken (token);
			claims.put ("created", projectDate.getCurrentTime ());
			refreshToken = this.generateToken (claims);
		} catch (Exception e) {
			refreshToken = null;
		}
		return refreshToken;
	}

	public Boolean validateToken (String token, UserDetails userDetails) {

		final String username = this.getUserFromToken (token);

		return (username.equals (userDetails.getUsername ()));
	}
}
