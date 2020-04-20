package com.airport.security.filter;

import com.airport.exceptions.EntityNotFoundException;
import com.airport.exceptions.JwtAuthenticationException;
import com.airport.security.config.SecurityConstants;
import com.airport.security.controller.request.AuthenticationRequest;
import com.airport.security.util.PassengerAuthService;
import com.airport.security.util.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private final AuthenticationManager authenticationManager;

	private final PassengerAuthService passengerAuthService;

	private final TokenUtil tokenUtil;

	private final SecurityConstants securityConstants;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
	                                            HttpServletResponse res) {
		try {
			AuthenticationRequest passenger = new ObjectMapper ()
					                        .readValue(req.getInputStream(), AuthenticationRequest.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken (
							passenger.getLogin (),
							passenger.getPassword())
			);
		} catch (IOException e) {
			throw new JwtAuthenticationException (e.getMessage (), new EntityNotFoundException ());
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req,
	                                        HttpServletResponse res,
	                                        FilterChain chain,
	                                        Authentication auth){

		String token = tokenUtil.generateToken (passengerAuthService.loadUserByUsername (auth.getName ()));

		res.addHeader((securityConstants.getHeader ()), securityConstants.getPrefix () + token);
	}
}