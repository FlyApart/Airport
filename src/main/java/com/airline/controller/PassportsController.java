package com.airline.controller;



import com.airline.entity.Passports;
import com.airline.repository.PassportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/rest/passports")
public class PassportsController {
	@Autowired
	PassportDao passportDao;

	@GetMapping
	public ResponseEntity<List<Passports>> getPassengers() {
		ResponseEntity <List<Passports>> response = new ResponseEntity<>(passportDao.findAll(), HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "/{passengerId}")
	public ResponseEntity <List<Passports>> findPassportsByPassengersId (@PathVariable ("passengerId") Long passengerId){

		return new ResponseEntity<>(passportDao.findByPassengersId (passengerId), HttpStatus.OK);
	}


}
