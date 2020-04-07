package com.airport.controller;

import com.airport.controller.request.change.FlightUpdateRequest;
import com.airport.controller.request.create.FlightSaveRequest;
import com.airport.entity.Flights;
import com.airport.repository.FlightsDao;
import com.airport.service.FlightService;
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
	@RequestMapping("/rest/Flights")
	@RequiredArgsConstructor
	public class FlightsController {


		private final FlightsDao flightsDao;

		private final FlightService flightsService;


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

