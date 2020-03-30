package com.airline.controller;

import com.airline.controller.request.PassengerSaveRequest;
import com.airline.controller.request.PassengerUpdateRequest;
import com.airline.entity.Passengers;
import com.airline.repository.PassengerDao;
import com.airline.service.PassengersService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rest/passengers")
@RequiredArgsConstructor
public class PassengersController {

	private final PassengerDao passengersDao;

	private final PassengersService passengersService;

	@GetMapping
	public ResponseEntity<List<Passengers>> findAll () {
		return new ResponseEntity<> (passengersDao.findAll (), HttpStatus.OK);
	}


	@GetMapping(value = "/{id}")
	public ResponseEntity<Passengers> findPassengerById (@PathVariable Long id) {

		return new ResponseEntity<> (passengersDao.findById (id), HttpStatus.OK);
	}

	@PostMapping
	@Transactional                              //  TODO add (rollbackFor =)
	public ResponseEntity<Passengers> createPassenger (@RequestBody @Valid PassengerSaveRequest passengerInfo) {
		//        roleDao.save(new Role(savedUser.getUserId(), "ROLE_USER"));
		return new ResponseEntity<> (passengersService.save (passengerInfo), HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Long> deletePassenger (@PathVariable("id") Long id) {
		passengersDao.delete (id);
		return new ResponseEntity<> (id, HttpStatus.OK);
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

	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Passengers> updatePassenger (@ApiParam(value = "User ID", required = true) @PathVariable("id") Long id, @RequestBody @Valid PassengerUpdateRequest passengerInfo) {
		return new ResponseEntity<> (passengersService.update (passengerInfo, id), HttpStatus.OK);
	}


}
