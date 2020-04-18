package com.airport.security.controller;

import com.airport.controller.request.create.PassengerSaveRequest;
import com.airport.entity.Passenger;
import com.airport.service.PassengersService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

	private final PassengersService passengersService;

	private final ConversionService conversionService;

	@Transactional (rollbackFor = Exception.class)
	@PostMapping
	public ResponseEntity<Passenger> registration (@RequestBody @Valid PassengerSaveRequest passengerInfo) {
		Passenger passenger = conversionService.convert (passengerInfo, Passenger.class);
		return new ResponseEntity<> (passengersService.saveAndUpdate (passenger), HttpStatus.CREATED);
	}

	@Transactional (rollbackFor = Exception.class)
	@GetMapping(value = "/activate/{code}")
	public ResponseEntity<String> activate (@PathVariable String code) {
		return ResponseEntity.ok (passengersService.activate(code));
	}
}
