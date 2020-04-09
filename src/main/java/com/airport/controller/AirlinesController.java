package com.airport.controller;


import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.change.AirlinesUpdateRequest;
import com.airport.controller.request.create.AirlinesSaveRequest;
import com.airport.entity.Airlines;
import com.airport.repository.springdata.AirlinesRepository;
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
@RequestMapping(value = "/rest/airlines")
@RequiredArgsConstructor
public class AirlinesController {
    
	private final  AirlinesRepository airlinesRepository;
	private final ConversionService conversionService;

	@ApiImplicitParams(
			{@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(, " +
					          "\"asc or desc\"). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
	@GetMapping
	public ResponseEntity<Page< Airlines>> findAllAirlines(@ApiIgnore Pageable pageable) {
		ResponseEntity <Page< Airlines>> response = new ResponseEntity<>( airlinesRepository.findAll(pageable), HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity < Airlines> findAirlinesById (@PathVariable ("id") String id){
		 Airlines  airlines =  airlinesRepository.findById (Long.valueOf (id))
                .orElseThrow (() -> new EntityNotFoundException ( Airlines.class, id));
		return new ResponseEntity<>( airlines, HttpStatus.OK);
	}
	

	@Transactional
	@DeleteMapping(value = "/{id}")
	public String DeleteAirlines (@PathVariable ("id") String id){
		 Airlines  airlines =  airlinesRepository.findById (Long.valueOf (id))
		                                         .orElseThrow(() -> new EntityNotFoundException ( Airlines.class, id));
		 airlinesRepository.deleteCities (airlines);
		return id;
	}

	@PostMapping
	@Transactional
	public ResponseEntity< Airlines> createAirlines (@RequestBody @Valid  AirlinesSaveRequest  airlinesSaveRequest) {
		 Airlines  airlines = conversionService.convert (airlinesSaveRequest, Airlines.class);
		return new ResponseEntity<> ( airlinesRepository.saveAndFlush (airlines), HttpStatus.CREATED);
	}

	@PutMapping (value = "/{id}")
	@Transactional
	public ResponseEntity< Airlines> updateAirlines (@PathVariable ("id") String id,
                                                       @RequestBody @Valid  AirlinesUpdateRequest  AirlinesUpdateRequest) {
		 AirlinesUpdateRequest.setId(id);
		 Airlines  airlines = conversionService.convert ( AirlinesUpdateRequest,  Airlines.class);
		return new ResponseEntity<> ( airlinesRepository.saveAndFlush ( airlines), HttpStatus.CREATED);
	}


}
