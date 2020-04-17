package com.airport.security.controller;

import com.airport.security.controller.request.AuthenticationRequest;
import com.airport.security.util.TokenUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")

public class AuthenticationControllerTest {

	private final TokenUtil tokenUtil;

	private final UserDetailsService userDetailsService;

	private final AuthenticationManager authenticationManager;

	/*@ApiOperation(value = "Login passenger", notes = "Return Auth.Token")
	@PostMapping
	public ResponseEntity<AuthResponse> login (@RequestBody @Valid AuthenticationRequest request) {

		Authentication authenticate = authenticationManager.authenticate (
				new UsernamePasswordAuthenticationToken (
						request.getLogin (),
						request.getPassword ()));

		SecurityContextHolder.getContext ()
		                     .setAuthentication (authenticate);

		String authToken = tokenUtil.generateToken (userDetailsService.loadUserByUsername (request.getLogin ()));

		return ResponseEntity.ok (AuthResponse.builder ()
		                                      .login (request.getLogin ())
		                                      .authToken (authToken)
		                                      .build ());
	}*/
	@ApiImplicitParams({
	@ApiImplicitParam(name = "Auth-Token", value = "JWT", required = true, dataType = "string", paramType = "header")})
	@ApiOperation(value = "Login passenger", notes = "Return Auth.Token")
	@PostMapping
	public ResponseEntity<String> login (@RequestBody @Valid AuthenticationRequest request) {

		return ResponseEntity.ok ("AuthResponse.builder () ");
	}
}
