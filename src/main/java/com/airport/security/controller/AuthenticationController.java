package com.airport.security.controller;

import com.airport.repository.springdata.PassengersRepository;
import com.airport.security.model.AuthResponse;
import com.airport.security.model.AuthenticationRequest;
import com.airport.security.util.TokenUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class AuthenticationController {

	private final PassengersRepository passengersRepository;

	private final TokenUtil tokenUtil;

	private final UserDetailsService userDetailsService;

	private final AuthenticationManager authenticationManager;

	@ApiOperation (value = "Login passenger", notes = "Return Auth.Token")
	@PostMapping
	public ResponseEntity <AuthResponse> login(@RequestBody @Valid AuthenticationRequest request){

		Authentication authenticate = authenticationManager.authenticate (new UsernamePasswordAuthenticationToken (
				request.getLogin (),
				request.getPassword ()
		));

		SecurityContextHolder.getContext ().setAuthentication (authenticate);

		String authToken = tokenUtil.generateToken (userDetailsService.loadUserByUsername (request.getLogin ()));

		/*Passengers passenger = passengersRepository.findByLogin (request.getLogin ())
		                    .orElseThrow (()-> new EntityNotFoundException ("login = "+request.getLogin (),Passengers.class));
*/
		return ResponseEntity.ok (AuthResponse
				                          .builder ()
				                          //.id (passenger.getId ())
				                          .login (request.getLogin ())
				                          .authToken (authToken)
		                                             .build ());
	}
}
