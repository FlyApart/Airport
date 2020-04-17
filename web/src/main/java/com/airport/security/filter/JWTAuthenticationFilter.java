package com.airport.security.filter;

import com.airport.security.controller.request.AuthenticationRequest;
import com.airport.security.util.TokenUtil;
import com.airport.service.impl.PassengerAuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	private final PassengerAuthService passengerAuthService;

	private final TokenUtil tokenUtil;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
	                                            HttpServletResponse res) throws AuthenticationException {
		try {
			AuthenticationRequest creds = new ObjectMapper ()
					                        .readValue(req.getInputStream(), AuthenticationRequest.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken (
							creds.getLogin (),
							creds.getPassword())
			);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req,
	                                        HttpServletResponse res,
	                                        FilterChain chain,
	                                        Authentication auth) throws IOException, ServletException {

		String token = tokenUtil.generateToken (passengerAuthService.loadUserByUsername (auth.getName ()));

		res.addHeader("HEADER_STRING", "TOKEN_PREFIX " + token);
	}
}