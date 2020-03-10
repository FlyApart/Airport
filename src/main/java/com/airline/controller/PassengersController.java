package com.airline.controller;

import com.airline.controller.request.PassengerRequest;
import com.airline.entity.Passengers;
import com.airline.repository.CountryDao;
import com.airline.repository.PassengersDao;
import com.airline.repository.PassportDao;
import com.airline.service.PassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping ("/rest/passengers")
@Transactional
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
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Passengers> createPassenger(@RequestBody @Valid PassengerRequest passengerInfo) {
		//        roleDao.save(new Role(savedUser.getUserId(), "ROLE_USER"));
		return new ResponseEntity<>(passengersService.save (passengerInfo), HttpStatus.OK);
	}

	/*@PutMapping(value = "/{id}")
	@Transactional
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Passengers> updatePassenger(@PathVariable("id") Long id, @RequestBody @Valid PassengerRequestExample request) {

		Passengers passengers = passengersDao.findById (id);

		passengers.setName (request.getName());
		passengers.setSecondName (request.getSecondName());
		passengers.setBirthDate(request.getBirthDate());
		passengers.setChanged (new Timestamp (System.currentTimeMillis ()));
		passengers.setCountry (request.getCountry ());
		passengers.setLogin(request.getLogin());
		passengers.setPassword(request.getPassword());

		Passengers update = passengersDao.update(passengers);
		//        roleDao.save(new Role(savedUser.getUserId(), "ROLE_USER"));
		return new ResponseEntity<>(update, HttpStatus.OK);
	}*/

	@DeleteMapping (value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
    @Transactional
	public ResponseEntity<Long> deletePassenger(@PathVariable("id") Long id) {
		passengersDao.delete (id);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}



}
