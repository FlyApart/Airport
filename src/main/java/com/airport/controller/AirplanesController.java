package com.airport.controller;


import com.airport.controller.request.change.AirplanesUpdateRequest;
import com.airport.controller.request.create.AirplanesSaveRequest;
import com.airport.entity.Airplanes;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.AirplanesRepository;
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
@RequestMapping(value = "/rest/airplanes")
@RequiredArgsConstructor
public class AirplanesController {

	private final AirplanesRepository airplanesRepository;
	private final ConversionService conversionService;

	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
					value = "Sorting criteria in the format: property(, " + "\"asc or desc\"). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
	@GetMapping
	public ResponseEntity<Page<Airplanes>> findAllCities (@ApiIgnore Pageable pageable) {
		ResponseEntity<Page<Airplanes>> response = new ResponseEntity<> (airplanesRepository.findAll (pageable), HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Airplanes> findCitiesById (@PathVariable("id") String id) {
		Airplanes airplanes = airplanesRepository.findById (Long.valueOf (id))
		                                         .orElseThrow (() -> new EntityNotFoundException (Airplanes.class, id));
		return new ResponseEntity<> (airplanes, HttpStatus.OK);
	}


	@Transactional
	@DeleteMapping(value = "/{id}")
	public String DeleteCities (@PathVariable("id") String id) {
		Airplanes airplanes = airplanesRepository.findById (Long.valueOf (id))
		                                         .orElseThrow (() -> new EntityNotFoundException (Airplanes.class, id));
		airplanesRepository.delete (airplanes);
		return id;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Airplanes> createCities (@RequestBody @Valid AirplanesSaveRequest AirplanesSaveRequest) {
		Airplanes airplanes = conversionService.convert (AirplanesSaveRequest, Airplanes.class);
		return new ResponseEntity<> (airplanesRepository.saveAndFlush (airplanes), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Airplanes> updateCities (@PathVariable("id") String id, @RequestBody @Valid AirplanesUpdateRequest AirplanesUpdateRequest) {
		AirplanesUpdateRequest.setId (id);
		Airplanes airplanes = conversionService.convert (AirplanesUpdateRequest, Airplanes.class);
		return new ResponseEntity<> (airplanesRepository.saveAndFlush (airplanes), HttpStatus.CREATED);
	}


}
