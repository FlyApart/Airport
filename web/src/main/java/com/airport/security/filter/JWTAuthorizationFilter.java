package com.airport.security.filter;

import com.airport.security.ApplicationHeaders;
import com.airport.security.util.TokenUtil;
import com.airport.service.impl.PassengerAuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private final TokenUtil tokenUtil;

	private final PassengerAuthService passengerAuthService;

	public JWTAuthorizationFilter (AuthenticationManager authenticationManager,PassengerAuthService passengerAuthService,TokenUtil tokenUtil) {
		super (authenticationManager);
		this.tokenUtil =tokenUtil;
		this.passengerAuthService = passengerAuthService;
	}

	@Override
	protected void doFilterInternal (HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		String header = req.getHeader (ApplicationHeaders.AUTH_TOKEN);

		if (header == null || !header.startsWith ("TOKEN_PREFIX ")) {
			chain.doFilter (req, res);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication (req);

		SecurityContextHolder.getContext ()
		                     .setAuthentication (authentication);

		chain.doFilter (req, res);
	}



	private UsernamePasswordAuthenticationToken getAuthentication (HttpServletRequest request) {
		String token = request.getHeader (ApplicationHeaders.AUTH_TOKEN);
		if (token != null) {

			String username = tokenUtil.getUserFromToken (token);

			if (username != null && SecurityContextHolder.getContext ()
			                                             .getAuthentication () == null) {

				UserDetails passengerDetails = passengerAuthService.loadUserByUsername (username);

				if (tokenUtil.validateToken (token, passengerDetails)) {
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken (
							passengerDetails, null, passengerDetails.getAuthorities ());

					authenticationToken.setDetails (new WebAuthenticationDetailsSource ().buildDetails (request));

					return authenticationToken;
				}
			}
		}
		return null;
	}
}