package com.airport.controller;

import com.airport.controller.request.change.FlightsUpdateRequest;
import com.airport.controller.request.create.FlightsSaveRequest;
import com.airport.entity.Flights;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.AirlinesRepository;
import com.airport.repository.springdata.FlightsRepository;
import com.airport.service.FlightService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
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
	private final AirlinesRepository airlinesRepository;

	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
					value = "Sorting criteria in the format: property(, " + "\"asc or desc\"). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
	@GetMapping
	public ResponseEntity<Page<Flights>> findAll (@ApiIgnore Pageable pageable) {
		return new ResponseEntity<> (flightsRepository.findAll (pageable), HttpStatus.OK);
	}


	@GetMapping(value = "/{id}")
	public ResponseEntity<Flights> findFlightById (@PathVariable String id) {
		Flights flights = flightsRepository.findById (Long.valueOf (id))
		                                   .orElseThrow (() -> new EntityNotFoundException (Flights.class, id));
		return new ResponseEntity<> (flights, HttpStatus.OK);
	}
//todo delete example
	/*@GetMapping(value = "aaa")
	public ResponseEntity<List<Flights>> findFlightById () {
		List<Airline> airlines = new ArrayList<> ();
		airlines.add (airlinesRepository.getOne (1L));
		airlines.add (airlinesRepository.getOne (2L));
		List<Flights> flights = flightsRepository.findByIdsJoinA (airlines)
		                                   .orElseThrow (() -> new EntityNotFoundException ("",Flights.class));
		return new ResponseEntity<> (flights, HttpStatus.OK);
	}*/


	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<String> deleteFlight (@PathVariable String id) {
		Flights flights = (flightsRepository.findById (Long.valueOf (id))).orElseThrow (() -> new EntityNotFoundException (Flights.class, id));
		flightsRepository.delete (flights);
		return new ResponseEntity<> (id, HttpStatus.OK);
	}

	@Transactional
	@PostMapping
	public ResponseEntity<Flights> createFlight (@RequestBody @Valid FlightsSaveRequest flightsSaveRequest) {
		Flights flights = Objects.requireNonNull (conversionService.convert (flightsSaveRequest, Flights.class));
		return new ResponseEntity<> (flightsRepository.saveAndFlush (flights), HttpStatus.CREATED);

	}

	@Transactional
	@PutMapping(value = "/{id}")
	public ResponseEntity<Flights> updateFlight (@PathVariable String id, @RequestBody @Valid FlightsUpdateRequest flightsUpdateRequest) {
		flightsUpdateRequest.setId (Long.valueOf (id));
		Flights flights = Objects.requireNonNull (conversionService.convert (flightsUpdateRequest, Flights.class));
		return new ResponseEntity<> (flightsRepository.saveAndFlush (flights), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}/discounts")
	@Transactional
	public ResponseEntity<Flights> deleteDiscountsInFlight (@ApiParam (required = true, value = "Id of flight") @PathVariable String id,
	                                                    @ApiParam(value = "Ids of discounts for delete",required = true,type = "long")
	                                                    @RequestBody List<Long> discountId) {

		return new ResponseEntity<> (flightService.deleteFlightDiscounts (id, new ArrayList<> (discountId)), HttpStatus.OK);
	}
}

