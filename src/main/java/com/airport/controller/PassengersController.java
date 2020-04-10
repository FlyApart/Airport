package com.airport.controller;

import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.change.PassengerUpdateRequest;
import com.airport.controller.request.create.PassengerSaveRequest;
import com.airport.entity.Passengers;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.service.PassengersService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/rest/passengers")
@RequiredArgsConstructor
public class PassengersController {

	private final PassengersRepository passengersRepository;

	private final PassengersService passengersService;

	private final ConversionService conversionService;


	@ApiImplicitParams({@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(, " + "\"asc or desc\"). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
	@GetMapping
	public ResponseEntity<Page<Passengers>> getAllPassengers (@ApiIgnore Pageable pageable) {
		passengersRepository.findAll ();
		return new ResponseEntity<> (passengersRepository.findAll (pageable), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Passengers> findPassengerById (@PathVariable String id) {
		Passengers passengers = passengersRepository.findById (Long.valueOf (id))
		                                           .orElseThrow (() -> new EntityNotFoundException (Passengers.class, id));
		return new ResponseEntity<> (passengers, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	// @Transactional
	public ResponseEntity<String> deletePassenger (@PathVariable String id) {
		Passengers passengers = (passengersRepository.findById (Long.valueOf (id))).orElseThrow (() -> new EntityNotFoundException (Passengers.class, id));
		passengersRepository.delete (passengers);
		return new ResponseEntity<> (id, HttpStatus.OK);
	}

	@Transactional
	@PostMapping
	public ResponseEntity<Passengers> createPassenger (@RequestBody @Valid PassengerSaveRequest passengerInfo) {
		Passengers passengers = conversionService.convert (passengerInfo, Passengers.class);
		if (passengerInfo==null) return new ResponseEntity<> (passengersRepository.saveAndFlush (passengers), HttpStatus.CREATED);
			else return new ResponseEntity<> (passengersRepository.saveAndFlushPassengers (passengers), HttpStatus.CREATED);

	}


	@PutMapping(value = "/{id}")
	public ResponseEntity<Passengers> updatePassenger (@PathVariable String id, @RequestBody @Valid PassengerUpdateRequest passengerInfo) {
		passengerInfo.setId (id);
		return new ResponseEntity<> (passengersRepository.findById (passengersService.saveAndUpdate (conversionService.convert (passengerInfo, Passengers.class))
		                                                                             .getId ()).get (), HttpStatus.OK);

	}
}
