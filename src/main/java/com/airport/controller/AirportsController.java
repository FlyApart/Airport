package com.airport.controller;


import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.change.AirportsUpdateRequest;
import com.airport.controller.request.create.AirportsSaveRequest;
import com.airport.entity.Airports;
import com.airport.repository.springdata.AirportsRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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

	@ApiImplicitParams(
			{@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(, " +
					          "\"asc or desc\"). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
	@GetMapping
	public ResponseEntity<Page<Airports>> findAllAirports(@ApiIgnore Pageable pageable) {
		ResponseEntity <Page<Airports>> response = new ResponseEntity<>(airportsRepository.findAll(pageable), HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity <Airports> findAirportsById (@PathVariable ("id") String id){
		Airports airports = airportsRepository.findById (Long.valueOf (id))
                .orElseThrow (() -> new EntityNotFoundException (Airports.class, id));
		return new ResponseEntity<>(airports, HttpStatus.OK);
	}
	

	@Transactional
	@DeleteMapping(value = "/{id}")
	public String DeleteAirports (@PathVariable ("id") String id){
		Airports airports = airportsRepository.findById (Long.valueOf (id))
		                                         .orElseThrow(() -> new EntityNotFoundException (Airports.class, id));
		airportsRepository.deleteAirports (airports);
		return id;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Airports> createAirports (@RequestBody @Valid AirportsSaveRequest AirportsSaveRequest) {
		Airports airports = conversionService.convert (AirportsSaveRequest,Airports.class);
		return new ResponseEntity<> (airportsRepository.saveAndFlush (airports), HttpStatus.CREATED);
	}

	@PutMapping (value = "/{id}")
	@Transactional
	public ResponseEntity<Airports> updateAirports (@PathVariable ("id") String id,
                                                       @RequestBody @Valid AirportsUpdateRequest AirportsUpdateRequest) {
		AirportsUpdateRequest.setId(id);
		Airports airports = conversionService.convert (AirportsUpdateRequest, Airports.class);
		return new ResponseEntity<> (airportsRepository.saveAndFlush (airports), HttpStatus.CREATED);
	}


}
