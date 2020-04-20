package com.airport.controller;


import com.airport.controller.request.change.DiscountsUpdateRequest;
import com.airport.controller.request.create.DiscountsSaveRequest;
import com.airport.entity.Discounts;
import com.airport.entity.Status;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.DiscountsRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/rest/discounts")
@RequiredArgsConstructor
public class DiscountsController {

	private final DiscountsRepository discountsRepository;
	private final ConversionService conversionService;

	@ApiOperation(value = "Find all discounts")
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
	public ResponseEntity<Page<Discounts>> findAllDiscounts (@ApiIgnore Pageable pageable) {
		return new ResponseEntity<> (discountsRepository.findAll (pageable), HttpStatus.OK);
	}

	@ApiOperation(value = "Find discount by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Discounts> findDiscountsById (@PathVariable("id") String id) {
		Discounts discounts = discountsRepository.findById (Long.valueOf (id))
		                                         .orElseThrow (() -> new EntityNotFoundException (Discounts.class, id));
		return new ResponseEntity<> (discounts, HttpStatus.OK);
	}


	@ApiOperation(value = "Hard delete discount by id")
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
	public String hardDeleteDiscounts (@PathVariable("id") String id) {
		Discounts discounts = discountsRepository.findById (Long.valueOf (id))
		                                         .orElseThrow (() -> new EntityNotFoundException (Discounts.class, id));
		discountsRepository.delete (discounts);
		return id;
	}

	@ApiOperation(value = "Safe delete discount by id")
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
	public ResponseEntity<Discounts> safeDeleteDiscounts (@PathVariable("id") String id) {
		Discounts discounts = discountsRepository.findById (Long.valueOf (id))
		                                         .orElseThrow (() -> new EntityNotFoundException (Discounts.class, id));
		discounts.setStatus (Status.DELETED);
		return ResponseEntity.ok (discountsRepository.saveAndFlush (discounts));
	}

	@ApiOperation(value = "Save new discount")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@PostMapping
	@Transactional (rollbackFor = Exception.class)
	public ResponseEntity<Discounts> createDiscounts (@RequestBody @Valid DiscountsSaveRequest discountsSaveRequest) {
		Discounts discounts = conversionService.convert (discountsSaveRequest, Discounts.class);
		return new ResponseEntity<> (discountsRepository.saveAndFlush (discounts), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update discount by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@PutMapping(value = "/{id}")
	@Transactional (rollbackFor = Exception.class)
	public ResponseEntity<Discounts> updateDiscounts (@PathVariable("id") String id, @RequestBody @Valid DiscountsUpdateRequest discountsUpdateRequest) {
		discountsUpdateRequest.setId (id);
		Discounts discounts = conversionService.convert (discountsUpdateRequest, Discounts.class);
		return new ResponseEntity<> (discountsRepository.saveAndFlush (discounts), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Find discount by status and cost")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParams ({
			@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header"),
			@ApiImplicitParam(name = "status", dataTypeClass = Status.class, paramType = "query", value = "Status of airplane", required = true),
			@ApiImplicitParam(name = "cost", dataType = "string", paramType = "query", value = "Cost of discounts",  required = true),
	})
	@GetMapping(value = "/search")
	public ResponseEntity<List<Discounts>> findCostAndStatus (@ApiIgnore String cost, Status status) {
		Optional<List<Discounts>> listDiscount = discountsRepository.findByStatusAndCostGreaterThan (status, Double.valueOf (cost));
		return listDiscount.map (discounts -> new ResponseEntity<> (discounts, HttpStatus.OK))
		                    .orElseGet (() -> new ResponseEntity<> (new ArrayList<> (), HttpStatus.OK));
	}

}
