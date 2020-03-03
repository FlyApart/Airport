package com.airline.controller;

import com.airline.controller.request.PassengerInfoRequest;
import com.airline.controller.request.PassengerRequestExample;
import com.airline.entity.Passengers;
import com.airline.entity.Passports;
import com.airline.entity.vo.PassengersInfo;
import com.airline.service.PassengersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping ("/rest/passengers")
public class PassengersController {

	@Autowired
	private PassengersDetailService passengersDetailService;

	@GetMapping
	public ResponseEntity <List<PassengersInfo>> findAll (){
		return new ResponseEntity <>(passengersDetailService.findAll (), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PassengersInfo> findPassengerById (@PathVariable Long id){

		return new ResponseEntity <>(passengersDetailService.findById (id), HttpStatus.OK);
	}
	@PostMapping
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PassengersInfo> addPassenger (@RequestBody @Valid PassengerInfoRequest request){
		PassengersInfo passengersInfo = new PassengersInfo ();
		Passengers passengers = new Passengers ();
		passengers.setName (request.getName ());
		passengers.setSecondName (request.getSecondName ());
		passengers.setLogin (request.getLogin ());
		passengers.setPassword (request.getPassword ());
		passengers.setCountry (request.getCountry ());
		passengers.setBirthDate (request.getBirthDate ());
		passengers.setCreated ( new Timestamp (System.currentTimeMillis ()));
		passengersInfo.setPassengers (passengers);

		Passports passports = new Passports ();
		List <Passports> list = new ArrayList<> ();

		passports.setSeries (request.getSeries ());
		passports.setNumber (request.getNumber ());
		list.add (passports);
		passengersInfo.setPassports (list);

		PassengersInfo info = passengersDetailService.save (passengersInfo);
		return new ResponseEntity <>(info, HttpStatus.OK);
	}
}
