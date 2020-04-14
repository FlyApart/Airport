package com.airport.controller;

import com.airport.controller.request.change.PassengerUpdateRequest;
import com.airport.controller.request.create.PassengerSaveRequest;
import com.airport.entity.Passengers;
import com.airport.entity.Status;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.security.util.PrincipalUtil;
import com.airport.service.PassengersService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/rest/passengers")
@RequiredArgsConstructor
@Slf4j
public class PassengersController {

	private final PassengersRepository passengersRepository;

	private final PassengersService passengersService;

	private final ConversionService conversionService;

	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
					value = "Sorting criteria in the format: property(, " + "\"asc or desc\"). " + "Default sort order is ascending. " + "Multiple sort criteria are supported."),
			@ApiImplicitParam(name = "JWT", value = "JWT", required = true, dataType = "string", paramType = "header")})
	@GetMapping
	public ResponseEntity<Page<Passengers>> getAllPassengers (@ApiIgnore Pageable pageable) {
		passengersRepository.findAll ();
		return new ResponseEntity<> (passengersRepository.findAll (pageable), HttpStatus.OK);
	}

	@ApiImplicitParams({
			@ApiImplicitParam(name = "JWT", value = "JWT", required = true, dataType = "string", paramType = "header")})
	@GetMapping(value = "/{id}")
	public ResponseEntity<Passengers> findPassengerById (@PathVariable String id, @ApiIgnore Principal principal) {

		String passengerLogin = PrincipalUtil.getLogin (principal);
		Passengers passengerAuth = passengersRepository.findByLogin (passengerLogin)
		                                               .orElseThrow (() -> new EntityNotFoundException (Passengers.class, id));

		Passengers passengers = passengersRepository.findById (Long.valueOf (id))
		                                            .orElseThrow (() -> new EntityNotFoundException (Passengers.class, id));

		log.info ("lalala test" + passengerAuth.getLogin ());

		return new ResponseEntity<> (passengers, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deletePassenger (@PathVariable String id) {
		Passengers passengers = (passengersRepository.findById (Long.valueOf (id))).orElseThrow (() -> new EntityNotFoundException (Passengers.class, id));
		passengersRepository.delete (passengers);
		return new ResponseEntity<> (id, HttpStatus.OK);
	}

	@Transactional
	@PostMapping
	public ResponseEntity<Passengers> createPassenger (@RequestBody @Valid PassengerSaveRequest passengerInfo) {
		Passengers passengers = conversionService.convert (passengerInfo, Passengers.class);
		return new ResponseEntity<> (passengersService.saveAndUpdate (passengers), HttpStatus.CREATED);
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<Passengers> safeDeletePassenger (@PathVariable Long id) {
		Passengers passengers = (passengersRepository.findById (Long.valueOf (id))).orElseThrow (() -> new EntityNotFoundException (Passengers.class, id));
		passengers.setStatus (Status.DELETED);

		return new ResponseEntity<> (passengersRepository.saveAndFlush (passengers), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Passengers> updatePassenger (@PathVariable String id, @RequestBody @Valid PassengerUpdateRequest passengerInfo) {
		passengerInfo.setId (id);
		return new ResponseEntity<> (passengersService.saveAndUpdate (conversionService.convert (passengerInfo, Passengers.class)), HttpStatus.OK);

	}
}
