package com.airport.controller;

import com.airport.controller.request.create.PassengerSaveRequest;
import com.airport.entity.Passengers;
import com.airport.service.PassengersService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@Transactional
	@PostMapping
	public ResponseEntity<Passengers> createPassenger (@RequestBody @Valid PassengerSaveRequest passengerInfo) {
		Passengers passengers = conversionService.convert (passengerInfo, Passengers.class);
		return new ResponseEntity<> (passengersService.saveAndUpdate (passengers), HttpStatus.CREATED);
	}

}
