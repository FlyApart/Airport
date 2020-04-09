package com.airport.controller;


import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.change.DiscountsUpdateRequest;
import com.airport.controller.request.create.DiscountsSaveRequest;
import com.airport.entity.Discounts;
import com.airport.repository.springdata.DiscountsRepository;
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
@RequestMapping(value = "/rest/discounts")
@RequiredArgsConstructor
public class DiscountsController {
    
	private final  DiscountsRepository discountsRepository;
	private final ConversionService conversionService;

	@ApiImplicitParams(
			{@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(, " +
					          "\"asc or desc\"). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
	@GetMapping
	public ResponseEntity<Page< Discounts>> findAllDiscounts(@ApiIgnore Pageable pageable) {
		return new ResponseEntity<>( discountsRepository.findAll(pageable), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity < Discounts> findDiscountsById (@PathVariable ("id") String id){
		 Discounts  discounts =  discountsRepository.findById (Long.valueOf (id))
                .orElseThrow (() -> new EntityNotFoundException ( Discounts.class, id));
		return new ResponseEntity<>( discounts, HttpStatus.OK);
	}
	

	@Transactional
	@DeleteMapping(value = "/{id}")
	public String DeleteDiscounts (@PathVariable ("id") String id){
		 Discounts  discounts =  discountsRepository.findById (Long.valueOf (id))
		                                         .orElseThrow(() -> new EntityNotFoundException ( Discounts.class, id));
		 discountsRepository.deleteDiscounts (discounts);
		return id;
	}

	@PostMapping
	@Transactional
	public ResponseEntity< Discounts> createDiscounts (@RequestBody @Valid  DiscountsSaveRequest  discountsSaveRequest) {
		 Discounts  discounts = conversionService.convert (discountsSaveRequest, Discounts.class);
		return new ResponseEntity<> ( discountsRepository.saveAndFlush (discounts), HttpStatus.CREATED);
	}

	@PutMapping (value = "/{id}")
	@Transactional
	public ResponseEntity< Discounts> updateDiscounts (@PathVariable ("id") String id,
                                                       @RequestBody @Valid  DiscountsUpdateRequest  DiscountsUpdateRequest) {
		 DiscountsUpdateRequest.setId(id);
		 Discounts  discounts = conversionService.convert ( DiscountsUpdateRequest,  Discounts.class);
		return new ResponseEntity<> ( discountsRepository.saveAndFlush ( discounts), HttpStatus.CREATED);
	}


}
