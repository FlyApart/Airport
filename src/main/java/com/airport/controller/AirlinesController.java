package com.airport.controller;


import com.airport.controller.request.change.AirlinesUpdateRequest;
import com.airport.controller.request.create.AirlinesSaveRequest;
import com.airport.entity.Airline;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.AirlinesRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping(value = "/rest/airline")
@RequiredArgsConstructor
public class AirlinesController {

	private final AirlinesRepository airlinesRepository;
	private final ConversionService conversionService;



	@ApiOperation(value = "Find all airlines")
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
					value = "Sorting criteria in the format: property(, " + "\"asc or desc\"). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
	@GetMapping
	public ResponseEntity<Page<Airline>> findAllAirlines (@ApiIgnore Pageable pageable) {
		return new ResponseEntity<> (airlinesRepository.findAll (pageable), HttpStatus.OK);
	}

	@ApiOperation(value = "Find airline by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<Airline> findAirlinesById (@ApiParam (value = "id of the desired airline") @PathVariable("id") String id) {
		Airline airline = airlinesRepository.findById (Long.valueOf (id))
		                                    .orElseThrow (() -> new EntityNotFoundException (Airline.class, id));
		return new ResponseEntity<> (airline, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete airline by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@Transactional (rollbackFor = Exception.class)
	@DeleteMapping(value = "/{id}")
	public String DeleteAirlines (@ApiParam (value = "id of the desired airline") @PathVariable("id") String id) {
		Airline airline = airlinesRepository.findById (Long.valueOf (id))
		                                    .orElseThrow (() -> new EntityNotFoundException (Airline.class, id));
		airlinesRepository.deleteAirlines (airline);
		return id;
	}

	@ApiOperation(value = "Save new airline")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@PostMapping
	@Transactional
	public ResponseEntity<Airline> createAirlines (@RequestBody @Valid AirlinesSaveRequest airlinesSaveRequest) {
		Airline airline = conversionService.convert (airlinesSaveRequest, Airline.class);
		return new ResponseEntity<> (airlinesRepository.saveAndFlush (airline), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update airline by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Airline> updateAirlines (@ApiParam (value = "Id of the desired airline") @PathVariable("id") String id,
	                                               @ApiParam (value = "Possible values to change")@RequestBody @Valid AirlinesUpdateRequest airlinesUpdateRequest) {
		airlinesUpdateRequest.setId (id);
		Airline airline = conversionService.convert (airlinesUpdateRequest, Airline.class);
		return new ResponseEntity<> (airlinesRepository.saveAndFlush (airline), HttpStatus.CREATED);
	}


}
