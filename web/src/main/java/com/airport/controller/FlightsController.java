package com.airport.controller;

import com.airport.controller.request.change.FlightsUpdateRequest;
import com.airport.controller.request.create.FlightsSaveRequest;
import com.airport.entity.Flights;
import com.airport.entity.Status;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.FlightsRepository;
import com.airport.service.FlightService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@CrossOrigin
@RestController
@RequestMapping("/rest/flights")
@RequiredArgsConstructor
public class FlightsController {


	private final FlightsRepository flightsRepository;
	private final ConversionService conversionService;
	private final FlightService flightService;

	@ApiOperation(value = "Find all flights")
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
	public ResponseEntity<Page<Flights>> findAllFlight (@ApiIgnore Pageable pageable) {
		return new ResponseEntity<> (flightsRepository.findAll (pageable), HttpStatus.OK);
	}

	@ApiOperation(value = "Find flight by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Flights> findFlightById (@PathVariable String id) {
		Flights flights = flightsRepository.findById (Long.valueOf (id))
		                                   .orElseThrow (() -> new EntityNotFoundException (Flights.class, id));
		return new ResponseEntity<> (flights, HttpStatus.OK);
	}

	@ApiOperation(value = "Hard delete flight by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@DeleteMapping(value = "/hardDelete/{id}")
	public String hardDeleteFlight (@PathVariable String id) {
		Flights flights = (flightsRepository.findById (Long.valueOf (id)))
				                  .orElseThrow (() -> new EntityNotFoundException (Flights.class, id));
		flightsRepository.delete (flights);
		return id;
	}

	@ApiOperation(value = "Safe delete flight by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Flights> safeDeleteFlight (@PathVariable String id) {
		Flights flights = (flightsRepository.findById (Long.valueOf (id)))
				                  .orElseThrow (() -> new EntityNotFoundException (Flights.class, id));
		flights.setStatus (Status.DELETED);
		return ResponseEntity.ok (flightsRepository.saveAndFlush (flights));
	}

	@ApiOperation(value = "Delete discounts on flights by id of flights")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@DeleteMapping(value = "/{id}/discounts/{ids}")
	@Transactional (rollbackFor = Exception.class)
	public ResponseEntity<Flights> deleteDiscountsInFlight (@ApiParam(required = true, value = "Id of flight") @PathVariable String id,
	                                                        @ApiParam(value = "Ids of discounts for delete",required = true,type = "long")
	                                                        @PathVariable List<Long> ids) {

		return new ResponseEntity<> (flightService.deleteFlightDiscounts (id, new ArrayList<> (ids)), HttpStatus.OK);
	}

	@ApiOperation(value = "Save new flight")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@PostMapping
	@Transactional (rollbackFor = Exception.class)
	public ResponseEntity<Flights> createFlight (@RequestBody @Valid FlightsSaveRequest flightsSaveRequest) {
		Flights flights = Objects.requireNonNull (conversionService.convert (flightsSaveRequest, Flights.class));
		return new ResponseEntity<> (flightsRepository.saveAndFlush (flights), HttpStatus.CREATED);

	}

	@ApiOperation(value = "Update flights by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@PutMapping(value = "/{id}")
	@Transactional (rollbackFor = Exception.class)
	public ResponseEntity<Flights> updateFlight (@PathVariable String id, @RequestBody @Valid FlightsUpdateRequest flightsUpdateRequest) {
		flightsUpdateRequest.setId (Long.valueOf (id));
		Flights flights = Objects.requireNonNull (conversionService.convert (flightsUpdateRequest, Flights.class));
		return new ResponseEntity<> (flightsRepository.saveAndFlush (flights), HttpStatus.OK);
	}
}

