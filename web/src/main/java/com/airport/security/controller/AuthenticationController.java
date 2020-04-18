package com.airport.security.controller;

import com.airport.security.controller.request.AuthenticationRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

	@ApiOperation(value = "Login passenger", notes = "Return Auth.Token")
	@PostMapping
	public ResponseEntity<AuthenticationRequest> login (@RequestBody @Valid AuthenticationRequest request, @ApiIgnore Principal principal) {

		return ResponseEntity.ok (request);
	}
}
