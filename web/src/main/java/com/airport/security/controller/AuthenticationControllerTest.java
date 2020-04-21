package com.airport.security.controller;

import com.airport.security.controller.request.AuthenticationRequest;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AuthenticationControllerTest {

	@ApiImplicitParams({
	@ApiImplicitParam(name = "Auth-Token", value = "JWT", required = true, dataType = "string", paramType = "header")})
	@ApiOperation(value = "Login passenger", notes = "Return Auth.Token")
	@PostMapping
	@Transactional(Transactional.TxType.NEVER)
	public ResponseEntity<String> login (@RequestBody @Valid AuthenticationRequest request) {

		return ResponseEntity.ok ("AuthResponse.builder () ");
	}
}
