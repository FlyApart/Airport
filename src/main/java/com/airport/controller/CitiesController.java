package com.airport.controller;


import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.change.CitiesUpdateRequest;
import com.airport.controller.request.create.CitiesSaveRequest;
import com.airport.entity.Cities;
import com.airport.repository.springdata.CitiesRepository;
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
@RequestMapping(value = "/rest/cities")
@RequiredArgsConstructor
public class CitiesController {
    
	private final CitiesRepository citiesRepository;
	private final ConversionService conversionService;

	@ApiImplicitParams(
			{@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(, " +
					          "\"asc or desc\"). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
	@GetMapping
	public ResponseEntity<Page<Cities>> findAllCities(@ApiIgnore Pageable pageable) {
		ResponseEntity <Page<Cities>> response = new ResponseEntity<>(citiesRepository.findAll(pageable), HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity <Cities> findCitiesById (@PathVariable ("id") String id){
		Cities cities = citiesRepository.findById (Long.valueOf (id))
                .orElseThrow (() -> new EntityNotFoundException (Cities.class, id));
		return new ResponseEntity<>(cities, HttpStatus.OK);
	}
	

	@Transactional
	@DeleteMapping(value = "/{id}")
	public String DeleteCities (@PathVariable ("id") String id){
		Cities cities = citiesRepository.findById (Long.valueOf (id))
		                                         .orElseThrow(() -> new EntityNotFoundException (Cities.class, id));
		citiesRepository.deleteCities (cities);
		return id;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Cities> createCities (@RequestBody @Valid CitiesSaveRequest CitiesSaveRequest) {
		Cities cities = conversionService.convert (CitiesSaveRequest,Cities.class);
		return new ResponseEntity<> (citiesRepository.saveAndFlush (cities), HttpStatus.CREATED);
	}

	@PutMapping (value = "/{id}")
	@Transactional
	public ResponseEntity<Cities> updateCities (@PathVariable ("id") String id,
                                                       @RequestBody @Valid CitiesUpdateRequest CitiesUpdateRequest) {
		CitiesUpdateRequest.setId(id);
		Cities cities = conversionService.convert (CitiesUpdateRequest, Cities.class);
		return new ResponseEntity<> (citiesRepository.saveAndFlush (cities), HttpStatus.CREATED);
	}


}
