package com.airport.controller;


import com.airport.controller.request.create.TicketsSaveUpdateRequest;
import com.airport.entity.Flights;
import com.airport.entity.SeatsClass;
import com.airport.entity.Tickets;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.TicketsRepository;
import com.airport.service.TicketsService;
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
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/rest/tickets")
@RequiredArgsConstructor
public class TicketsController {

	private final TicketsRepository ticketsRepository;
	private final TicketsService ticketsService;
	private final ConversionService conversionService;

	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
					value = "Sorting criteria in the format: property(, " + "\"asc or desc\"). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
	@GetMapping
	public ResponseEntity<Page<Tickets>> findAllTickets (@ApiIgnore Pageable pageable) {
		ResponseEntity<Page<Tickets>> response = new ResponseEntity<> (ticketsRepository.findAll (pageable), HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Tickets> findTicketsById (@PathVariable("id") String id) {
		Tickets tickets = ticketsRepository.findById (Long.valueOf (id))
		                                   .orElseThrow (() -> new EntityNotFoundException (Tickets.class, id));
		return new ResponseEntity<> (tickets, HttpStatus.OK);
	}

	@GetMapping(value = "/flights/{id}")
	public ResponseEntity<List<String>> findTickets (@PathVariable("id") String id,
	                                                 @ApiParam("Class of place") SeatsClass seatsClass) {
		List<String> reservationPlaces = ticketsRepository.findPlacesByFlights (Long.valueOf (id), seatsClass)
		                                                  .orElseThrow (() -> new EntityNotFoundException ("id = " + id + ", seats class =" + seatsClass, Flights.class));
		return new ResponseEntity<> (reservationPlaces, HttpStatus.OK);
	}


	@Transactional
	@DeleteMapping(value = "/{id}")
	public String DeleteTickets (@PathVariable("id") String id) {
		Tickets tickets = ticketsRepository.findById (Long.valueOf (id))
		                                   .orElseThrow (() -> new EntityNotFoundException (Tickets.class, id));
		ticketsRepository.deleteTickets (tickets);
		return id;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Tickets> createTickets (@RequestBody @Valid TicketsSaveUpdateRequest ticketsSaveUpdateRequest) {
		return new ResponseEntity<> (ticketsService.saveAndUpdate (conversionService.convert (ticketsSaveUpdateRequest, Tickets.class)), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Tickets> updateTickets (@PathVariable("id") String id, @RequestBody @Valid TicketsSaveUpdateRequest ticketsSaveUpdateRequest) {
		ticketsSaveUpdateRequest.setId (id);
		return new ResponseEntity<> (ticketsService.saveAndUpdate (conversionService.convert (ticketsSaveUpdateRequest, Tickets.class)), HttpStatus.CREATED);
	}


}
