package com.airline.controller;

import com.airline.entity.vo.PassengersPassports;
import com.airline.service.PassengersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping ("/rest/passengers")
public class PassengersController {

	@Autowired
	private PassengersDetailService passengersDetailService;

	@GetMapping
	public ResponseEntity <List<PassengersPassports>> findAll (){
		return new ResponseEntity <>(passengersDetailService.getPassengersInfo(), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PassengersPassports> findPassengerInfo(@PathVariable Long id){

		return new ResponseEntity <>(passengersDetailService.findPassengerById (id), HttpStatus.OK);
	}
}
