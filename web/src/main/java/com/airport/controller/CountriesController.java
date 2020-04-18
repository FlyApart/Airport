package com.airport.controller;


import com.airport.controller.request.change.CountriesUpdateRequest;
import com.airport.controller.request.create.CountriesSaveRequest;
import com.airport.entity.Countries;
import com.airport.entity.Status;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.CountriesRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(value = "/rest/countries")
@RequiredArgsConstructor
public class CountriesController {

	private final CountriesRepository countriesRepository;
	private final ConversionService conversionService;

	@ApiOperation(value = "Find all available countries")
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
	public ResponseEntity<Page<Countries>> findAllCountries (@ApiIgnore Pageable pageable) {
		return new ResponseEntity<> (countriesRepository.findAll (pageable), HttpStatus.OK);
	}

	@ApiOperation(value = "Find country by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Countries> findCountriesById (@PathVariable("id") String id) {
		Countries countries = countriesRepository.findById (Long.valueOf (id))
		                                         .orElseThrow (() -> new EntityNotFoundException (Countries.class, id));
		return new ResponseEntity<> (countries, HttpStatus.OK);
	}


	@ApiOperation(value = "Hard delete country by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@Transactional (rollbackFor = Exception.class)
	@DeleteMapping(value = "/hardDelete/{id}")
	public String hardDeleteCountries (@PathVariable("id") String id) {
		Countries countries = countriesRepository.findById (Long.valueOf (id))
		                                         .orElseThrow (() -> new EntityNotFoundException (Countries.class, id));
		countriesRepository.delete (countries);
		return id;
	}

	@ApiOperation(value = "Safe delete country by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@Transactional (rollbackFor = Exception.class)
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Countries> safeDeleteCountries (@PathVariable("id") String id) {
		Countries countries = countriesRepository.findById (Long.valueOf (id))
		                                         .orElseThrow (() -> new EntityNotFoundException (Countries.class, id));
		countries.setStatus (Status.DELETED);
		return ResponseEntity.ok (countriesRepository.saveAndFlush (countries));
	}

	@ApiOperation(value = "Save new country")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@PostMapping
	@Transactional (rollbackFor = Exception.class)
	public ResponseEntity<Countries> createCountries (@RequestBody @Valid CountriesSaveRequest countriesSaveRequest) {
		Countries countries = conversionService.convert (countriesSaveRequest, Countries.class);
		return new ResponseEntity<> (countriesRepository.saveAndFlush (countries), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update country by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@PutMapping(value = "/{id}")
	@Transactional (rollbackFor = Exception.class)
	public ResponseEntity<Countries> updateCountries (@PathVariable("id") String id, @RequestBody @Valid CountriesUpdateRequest countriesUpdateRequest) {
		countriesUpdateRequest.setId (id);
		Countries countries = conversionService.convert (countriesUpdateRequest, Countries.class);
		return new ResponseEntity<> (countriesRepository.saveAndFlush (countries), HttpStatus.CREATED);
	}


}
