package com.airport.controller;


import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.change.CountriesUpdateRequest;
import com.airport.controller.request.create.CountriesSaveRequest;
import com.airport.entity.Countries;
import com.airport.repository.springdata.CountriesRepository;
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
@RequestMapping(value = "/rest/countries")
@RequiredArgsConstructor
public class CountriesController {
    
	private final CountriesRepository countriesRepository;
	private final ConversionService conversionService;

	@ApiImplicitParams(
			{@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(, " +
					          "\"asc or desc\"). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
	@GetMapping
	public ResponseEntity<Page<Countries>> findAllCountries(@ApiIgnore Pageable pageable) {
		return new ResponseEntity<>(countriesRepository.findAll(pageable), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity <Countries> findCountriesById (@PathVariable ("id") String id){
		Countries countries = countriesRepository.findById (Long.valueOf (id))
                .orElseThrow (() -> new EntityNotFoundException (Countries.class, id));
		return new ResponseEntity<>(countries, HttpStatus.OK);
	}
	

	@Transactional
	@DeleteMapping(value = "/{id}")
	public String DeleteCountries (@PathVariable ("id") String id){
		Countries countries = countriesRepository.findById (Long.valueOf (id))
		                                         .orElseThrow(() -> new EntityNotFoundException (Countries.class, id));
		countriesRepository.deleteCountries (countries);
		return id;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Countries> createCountries (@RequestBody @Valid CountriesSaveRequest countriesSaveRequest) {
		Countries countries = conversionService.convert (countriesSaveRequest,Countries.class);
		return new ResponseEntity<> (countriesRepository.saveAndFlush (countries), HttpStatus.CREATED);
	}

	@PutMapping (value = "/{id}")
	@Transactional
	public ResponseEntity<Countries> updateCountries (@PathVariable ("id") String id,
                                                       @RequestBody @Valid CountriesUpdateRequest countriesUpdateRequest) {
		countriesUpdateRequest.setId(id);
		Countries countries = conversionService.convert (countriesUpdateRequest, Countries.class);
		return new ResponseEntity<> (countriesRepository.saveAndFlush (countries), HttpStatus.CREATED);
	}


}
