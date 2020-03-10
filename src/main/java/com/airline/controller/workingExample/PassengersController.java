package com.airline.controller.workingExample;
/*
import com.airline.controller.request.PassengerInfoRequest;
import com.airline.entity.Passengers;
import com.airline.entity.Passports;
import com.airline.entity.vo.PassengersInfo;
import com.airline.service.PassengersService;
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
	*//*@Autowired
	private DefaultExceptionHandler defaultExceptionHandler;
*//*
	@Autowired
	private PassengersService passengersService;

	@GetMapping
	public ResponseEntity <List<PassengersInfo>> findAll (){
		return new ResponseEntity <>(passengersService.findAll (), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PassengersInfo> findPassengerById (@PathVariable Long id){

		return new ResponseEntity <>(passengersService.findById (id), HttpStatus.OK);
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

		PassengersInfo info = passengersService.save (passengersInfo);
		return new ResponseEntity <>(info, HttpStatus.OK);
	}

	*//*@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	//@Secured ("ROlE_ADMIN")
	public ResponseEntity<String> deletePassenger (@PathVariable Long id) {
		try{
			passengersService.findById (id);
		}catch (Exception e){
			defaultExceptionHandler.handleOthersException(e);
			return new ResponseEntity<>("Passenger with id = "+id+" not found",HttpStatus.BAD_REQUEST);
		}

		 passengersService.delete (id);
		return new ResponseEntity<>(HttpStatus.OK);
	}*//*
}*/
