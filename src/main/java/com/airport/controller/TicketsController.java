package com.airport.controller;


import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.create.SaveAndUpdateTicketsRequest;
import com.airport.entity.FlightsClass;
import com.airport.entity.Tickets;
import com.airport.repository.springdata.TicketsRepository;
import com.airport.service.TicketsService;
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
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/rest/tickets")
@RequiredArgsConstructor
public class TicketsController {
    
	private final  TicketsRepository ticketsRepository;
	private final ConversionService conversionService;
	private final TicketsService ticketsService;

	@ApiImplicitParams(
			{@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(, " +
					          "\"asc or desc\"). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
	@GetMapping
	public ResponseEntity<Page< Tickets>> findAllTickets(@ApiIgnore Pageable pageable) {
		ResponseEntity <Page< Tickets>> response = new ResponseEntity<>( ticketsRepository.findAll(pageable), HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity < Tickets> findTicketsById (@PathVariable ("id") String id){
		 Tickets  tickets =  ticketsRepository.findById (Long.valueOf (id))
                .orElseThrow (() -> new EntityNotFoundException ( Tickets.class, id));
		return new ResponseEntity<>( tickets, HttpStatus.OK);
	}

    @GetMapping(value = "/flights/{id}")
    public ResponseEntity <List<String>> findTickets (@PathVariable ("id") String id, FlightsClass flightsClass){
        List<String> reservationPlaces =  ticketsRepository.findPlacesByFlights(Long.valueOf (id),flightsClass)
                .orElseThrow (() -> new EntityNotFoundException ( Tickets.class, id));



        return new ResponseEntity<>( reservationPlaces, HttpStatus.OK);
    }
	

	@Transactional
	@DeleteMapping(value = "/{id}")
	public String DeleteTickets (@PathVariable ("id") String id){
		 Tickets  tickets =  ticketsRepository.findById (Long.valueOf (id))
		                                         .orElseThrow(() -> new EntityNotFoundException ( Tickets.class, id));
		 ticketsRepository.deleteTickets (tickets);
		return id;
	}



	@PostMapping
	@Transactional
	public ResponseEntity< Tickets> createTickets (@RequestBody @Valid SaveAndUpdateTicketsRequest ticketsSaveAndUpdateRequest) {
		return new ResponseEntity<> ( ticketsService.saveAndUpdate (ticketsSaveAndUpdateRequest), HttpStatus.CREATED);
	}

	@PutMapping (value = "/{id}")
	@Transactional
	public ResponseEntity< Tickets> updateTickets (@PathVariable ("id") String id,
                                                       @RequestBody @Valid  SaveAndUpdateTicketsRequest  ticketsSaveAndUpdateRequest) {
		ticketsSaveAndUpdateRequest.setId(id);
		return new ResponseEntity<> ( ticketsService.saveAndUpdate (ticketsSaveAndUpdateRequest), HttpStatus.CREATED);
	}


}
