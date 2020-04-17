package com.airport.security.controller;

import com.airport.security.controller.request.AuthenticationRequest;
import com.airport.security.util.TokenUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthenticationController {

	private final TokenUtil tokenUtil;

	private final UserDetailsService userDetailsService;

	private final AuthenticationManager authenticationManager;

	@ApiOperation(value = "Login passenger", notes = "Return Auth.Token")
	@PostMapping
	public ResponseEntity<String> login (@RequestBody @Valid AuthenticationRequest request, @ApiIgnore Principal principal) {

		return ResponseEntity.ok (principal.getName ());
	}
}
