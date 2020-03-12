package com.airline.controller;

import com.airline.controller.request.PassengerRequest;
import com.airline.entity.Passengers;
import com.airline.repository.CountryDao;
import com.airline.repository.PassengersDao;
import com.airline.repository.PassportDao;
import com.airline.repository.impl.PassportDaoImpl;
import com.airline.service.PassengersService;
import io.swagger.annotations.ApiParam;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping ("/rest/passengers")
public class PassengersController {

	@Autowired
	private PassengersDao passengersDao;
	@Autowired
	private PassengersService passengersService;


	@GetMapping
	public ResponseEntity <List<Passengers>> findAll (){
		return new ResponseEntity <>(passengersDao.findAll (), HttpStatus.OK);
	}


	@GetMapping(value = "/{id}")
	public ResponseEntity<Passengers> findPassengerById (@PathVariable Long id){

		return new ResponseEntity <>(passengersDao.findById (id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Passengers> createPassenger(@RequestBody @Valid PassengerRequest passengerInfo) {
		//        roleDao.save(new Role(savedUser.getUserId(), "ROLE_USER"));
		return new ResponseEntity<>(passengersService.save (passengerInfo), HttpStatus.OK);
	}

	@DeleteMapping (value = "/{id}")
	public ResponseEntity<Long> deletePassenger(@PathVariable("id") Long id) {
		passengersDao.delete (id);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}
		/*@PutMapping (value = "/{id}")
		public ResponseEntity<Passengers> updatePassenger(@ApiParam(value = "User ID", required = true) @PathVariable("id")  Long id,
		                                                  String Name,
		                                                  String Surname,
		                                                  String Password,
		                                                  String Country) {
			Passengers passengers = passengersDao.findById(id);
			if(Name!=null) passengers.setName (Name);
			if(Surname!=null) passengers.setSecondName (Surname);
			if(Password!=null) passengers.setName (Password);
			if(Country!=null) passengers.setName (Country);
			passengers.setChanged (new Timestamp (System.currentTimeMillis ()));
			return new ResponseEntity<>(passengersDao.update (passengers), HttpStatus.OK);
			*//*обновление паспорта!?*//*
		}*/

	@PutMapping (value = "/{id}")
		public ResponseEntity<Passengers> updatePassenger(@ApiParam(value = "User ID", required = true) @PathVariable("id")  Long id,
	                                                      @RequestBody @Valid PassengerRequest passengerInfo) {
			return new ResponseEntity<>(passengersService.update (passengerInfo, id), HttpStatus.OK);
		}



}
