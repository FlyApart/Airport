package com.airport.controller;

import com.airport.controller.request.change.PassengerUpdateRequest;
import com.airport.entity.Passenger;
import com.airport.entity.Status;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.security.util.PrincipalUtil;
import com.airport.service.PassengersService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/passenger")
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
			@ApiImplicitParam(name = "Auth-Token", value = "JWT", required = true, dataType = "string", paramType = "header")})
	@GetMapping
	public ResponseEntity<Page<Passenger>> getAllPassengers (@ApiIgnore Pageable pageable) {
		passengersRepository.findAll ();
		return new ResponseEntity<> (passengersRepository.findAll (pageable), HttpStatus.OK);
	}

	@ApiOperation(value = "Find passport by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Passenger> findPassengerById (@PathVariable String id, @ApiIgnore Principal principal) {


		Passenger passenger = passengersRepository.findById (Long.valueOf (id))
		                                          .orElseThrow (() -> new EntityNotFoundException (Passenger.class, id));
		if (PrincipalUtil.getAccess (principal,Long.valueOf (id))){
			return  ResponseEntity.ok (passenger);
		}
		return ResponseEntity.ok (new Passenger ());
	}

	@ApiOperation(value = "Hard delete passenger by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@DeleteMapping(value = "/hardDelete/{id}")
	public ResponseEntity<String> hardDeletePassenger (@PathVariable String id) {
		Passenger passenger = (passengersRepository.findById (Long.valueOf (id)))
				                      .orElseThrow (() -> new EntityNotFoundException (Passenger.class, id));
		passengersRepository.delete (passenger);
		return new ResponseEntity<> (id, HttpStatus.OK);
	}

	@ApiOperation(value = "Safe delete passenger by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Passenger> safeDeletePassenger (@PathVariable String id) {
		Passenger passenger = (passengersRepository.findById (Long.valueOf (id))).orElseThrow (() -> new EntityNotFoundException (Passenger.class, id));
		passenger.setStatus (Status.DELETED);
		return new ResponseEntity<> (passengersRepository.saveAndFlush (passenger), HttpStatus.OK);
	}

	@ApiOperation(value = "Update passport by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@PutMapping(value = "/{id}")
	@Transactional (rollbackFor = Exception.class)
	public ResponseEntity<Passenger> updatePassenger (@PathVariable String id, @RequestBody @Valid PassengerUpdateRequest passengerInfo) {
		passengerInfo.setId (id);
		return ResponseEntity.ok (passengersService.saveAndUpdate (conversionService.convert (passengerInfo, Passenger.class)));

	}
}
