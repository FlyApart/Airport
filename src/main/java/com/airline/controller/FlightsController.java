package com.airline.controller;

import com.airline.controller.request.FlightSaveRequest;
import com.airline.controller.request.FlightUpdateRequest;
import com.airline.controller.request.PassengerSaveRequest;
import com.airline.controller.request.PassengerUpdateRequest;
import com.airline.entity.Flights;
import com.airline.entity.Passengers;
import com.airline.repository.FlightsDao;
import com.airline.service.FlightService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


	@CrossOrigin
	@RestController
	@RequestMapping("/rest/Flights")
	@Transactional
	public class FlightsController {

		@Autowired
		private FlightsDao flightsDao;
		@Autowired
		private FlightService flightsService;


		@GetMapping
		public ResponseEntity<List<Flights>> findAll (){
			return new ResponseEntity <>(flightsDao.findAll (), HttpStatus.OK);
		}


		@GetMapping(value = "/{id}")
		public ResponseEntity<Flights> findPassengerById (@PathVariable Long id){

			return new ResponseEntity <>(flightsDao.findById (id), HttpStatus.OK);
		}

		@PostMapping
		@Transactional
		public ResponseEntity<Flights> createPassenger(@RequestBody @Valid FlightSaveRequest flightSaveRequest) {
			//        roleDao.save(new Role(savedUser.getUserId(), "ROLE_USER"));
			return new ResponseEntity<>(flightsService.save (flightSaveRequest), HttpStatus.OK);
		}

		@DeleteMapping (value = "/{id}")
		@Transactional
		public ResponseEntity<Long> deletePassenger(@PathVariable("id") Long id) {
			flightsDao.delete (id);
			return new ResponseEntity<>(id, HttpStatus.OK);
		}

		@PutMapping (value = "/{id}")
		@Transactional
		public ResponseEntity<Flights> updatePassenger(@ApiParam(value = "User ID", required = true) @PathVariable("id")  Long id,
		                                                  @RequestBody @Valid FlightUpdateRequest flightUpdateRequest) {
			return new ResponseEntity<>(flightsService.update (flightUpdateRequest, id), HttpStatus.OK);
		}
}

