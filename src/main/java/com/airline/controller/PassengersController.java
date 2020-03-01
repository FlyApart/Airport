package com.airline.controller;

import com.airline.entity.Passengers;
import com.airline.repository.PassengersDao;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/rest/passengers")
public class PassengersController {
    @Autowired
	private PassengersDao passengersDao;

	@GetMapping
	public ResponseEntity<List<Passengers>> getPassengers() {
		return new ResponseEntity<>(passengersDao.findAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "Get user from server by id")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successful getting user"),
			@ApiResponse(code = 400, message = "Invalid User ID supplied"),
			@ApiResponse(code = 401, message = "Lol kek"),
			@ApiResponse(code = 404, message = "User was not found"),
			@ApiResponse(code = 500, message = "Server error, something wrong"),
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<Passengers> getPassengersById(@ApiParam("User Path Id") @PathVariable Long id) {
		Passengers passengers = passengersDao.findById(id);
		return new ResponseEntity<>(passengers, HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Passengers> addPassengers(){
		return new ResponseEntity<> (HttpStatus.OK);
	}

}
