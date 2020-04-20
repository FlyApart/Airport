package com.airport.controller;


import com.airport.controller.request.change.AirplanesUpdateRequest;
import com.airport.controller.request.create.AirplanesSaveRequest;
import com.airport.entity.Airplanes;
import com.airport.entity.Status;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.AirplanesRepository;
import com.airport.service.AirplanesService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/rest/airplanes")
@RequiredArgsConstructor
public class AirplanesController {

	private final AirplanesRepository airplanesRepository;

	private final AirplanesService airplanesService;

	private final ConversionService conversionService;

	@ApiOperation(value = "Update airplane by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Error processing request")
	})

	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
					value = "Sorting criteria in the format: property(, " + "\"asc or desc\"). " + "Default sort order is ascending. " + "Multiple sort criteria are supported."),
			@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")})

	@GetMapping
	public ResponseEntity<Page<Airplanes>> findAllAirplane (@ApiIgnore Pageable pageable) {
		return new ResponseEntity<> (airplanesRepository.findAll (pageable), HttpStatus.OK);
	}

	@ApiOperation(value = "Find airplane by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Airplanes> findAirplaneById (@PathVariable("id") String id) {
		Airplanes airplanes = airplanesRepository.findById (Long.valueOf (id))
		                                         .orElseThrow (() -> new EntityNotFoundException (Airplanes.class, id));
		return new ResponseEntity<> (airplanes, HttpStatus.OK);
	}


	@ApiOperation(value = "Hard delete airplane by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@DeleteMapping(value = "/hardDelete/{id}")
	public String hardDeleteAirplane(@PathVariable("id") String id) {
		Airplanes airplanes = airplanesRepository.findById (Long.valueOf (id))
		                                         .orElseThrow (() -> new EntityNotFoundException (Airplanes.class, id));
		airplanesRepository.delete (airplanes);
		return id;
	}

	@ApiOperation(value = "Safe delete airplane by id")
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
	public Airplanes safeDeleteAirplane(@PathVariable("id") String id) {
		Airplanes airplanes = airplanesRepository.findById (Long.valueOf (id))
		                                         .orElseThrow (() -> new EntityNotFoundException (Airplanes.class, id));
		airplanes.setStatus (Status.DELETED);
		return airplanesRepository.saveAndFlush (airplanes);
	}


	@ApiOperation(value = "Save new airplane")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@PostMapping
	@Transactional (rollbackFor = Exception.class)
	public ResponseEntity<Airplanes> createAirplane (@RequestBody @Valid AirplanesSaveRequest airplanesSaveRequest) {
		Airplanes airplanes = conversionService.convert (airplanesSaveRequest, Airplanes.class);
		return new ResponseEntity<> (airplanesRepository.saveAndFlush (airplanes), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update airplane by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@PutMapping(value = "/{id}")
	@Transactional (rollbackFor = Exception.class)
	public ResponseEntity<Airplanes> updateAirplane (@PathVariable("id") String id, @RequestBody @Valid AirplanesUpdateRequest airplanesUpdateRequest) {
		airplanesUpdateRequest.setId (id);
		Airplanes airplanes = conversionService.convert (airplanesUpdateRequest, Airplanes.class);
		return new ResponseEntity<> (airplanesRepository.saveAndFlush (airplanes), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Find all seats by ids of airplane")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids", dataType = "long", paramType = "query", value = "Id of airplanes", allowMultiple = true, required = true),
			@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")})

	@GetMapping(value = "/seats")
	public ResponseEntity<List<Airplanes>> findSummarySeatById (@ApiIgnore Long[] ids) {
		Optional<List<Airplanes>> seat = airplanesRepository.findSummarySeatById (ids);
		return seat.map (airplanes -> new ResponseEntity<> (airplanes, HttpStatus.OK))
		           .orElseGet (() -> new ResponseEntity<> (new ArrayList<> (), HttpStatus.OK));
	}
	@ApiOperation(value = "Find airplane by criteria")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "date", dataType = "string", paramType = "query", value = "Date after which were built airplane, with format: \"yyyy-MM-dd\"", required = true),
			@ApiImplicitParam(name = "country", dataType = "String", paramType = "query", value = "Country of airplane",  required = true),
			@ApiImplicitParam(name = "status", dataTypeClass = Status.class, paramType = "query", value = "Status of airplane", required = true),
			@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	})
	@GetMapping(value = "/search")
	public ResponseEntity<List<Airplanes>> findByBuiltAfterAndCountriesNameAndStatus (@ApiIgnore String date, String country, Status status) {
		return  new ResponseEntity<> (airplanesService.searchByParam (date, country, status), HttpStatus.OK);
	}
}
