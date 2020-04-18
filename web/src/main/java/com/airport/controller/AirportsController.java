package com.airport.controller;


import com.airport.controller.request.change.AirportsUpdateRequest;
import com.airport.controller.request.create.AirportsSaveRequest;
import com.airport.entity.Airports;
import com.airport.entity.Status;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.AirportsRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
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

@CrossOrigin
@RestController
@RequestMapping(value = "/rest/airports")
@RequiredArgsConstructor
public class AirportsController {

	private final AirportsRepository airportsRepository;
	private final ConversionService conversionService;

	@ApiOperation(value = "Find all airports")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
					value = "Sorting criteria in the format: property(, " + "\"asc or desc\"). " + "Default sort order is ascending. " + "Multiple sort criteria are supported."),
			@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")})
	@GetMapping
	public ResponseEntity<Page<Airports>> findAllAirports (@ApiIgnore Pageable pageable) {
		return new ResponseEntity<> (airportsRepository.findAll (pageable), HttpStatus.OK);

	}

	@ApiOperation(value = "Find airport by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Airports> findAirportsById (@PathVariable("id") String id) {
		Airports airports = airportsRepository.findById (Long.valueOf (id))
		                                      .orElseThrow (() -> new EntityNotFoundException (Airports.class, id));
		return new ResponseEntity<> (airports, HttpStatus.OK);
	}


	@ApiOperation(value = "Hard delete airport by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@DeleteMapping(value = "/hardDelete/{id}")
	public String hardDeleteAirports (@PathVariable("id") String id) {
		Airports airports = airportsRepository.findById (Long.valueOf (id))
		                                      .orElseThrow (() -> new EntityNotFoundException (Airports.class, id));
		airportsRepository.delete (airports);
		return id;
	}

	@ApiOperation(value = "Safe delete airport by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@Transactional (rollbackFor = Exception.class)
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Airports> safeDeleteAirports (@PathVariable("id") String id) {
		Airports airports = airportsRepository.findById (Long.valueOf (id))
		                                      .orElseThrow (() -> new EntityNotFoundException (Airports.class, id));
		airports.setStatus (Status.DELETED);
		return ResponseEntity.ok (airportsRepository.saveAndFlush (airports));
	}

	@ApiOperation(value = "Save new airport")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@PostMapping
	@Transactional (rollbackFor = Exception.class)
	public ResponseEntity<Airports> createAirports (@RequestBody @Valid AirportsSaveRequest airportsSaveRequest) {
		Airports airports = conversionService.convert (airportsSaveRequest, Airports.class);
		return new ResponseEntity<> (airportsRepository.saveAndFlush (airports), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update airport by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@PutMapping(value = "/{id}")
	@Transactional (rollbackFor = Exception.class)
	public ResponseEntity<Airports> updateAirports (@PathVariable("id") String id, @RequestBody @Valid AirportsUpdateRequest airportsUpdateRequest) {
		airportsUpdateRequest.setId (id);
		Airports airports = conversionService.convert (airportsUpdateRequest, Airports.class);
		return new ResponseEntity<> (airportsRepository.saveAndFlush (airports), HttpStatus.CREATED);
	}


}
