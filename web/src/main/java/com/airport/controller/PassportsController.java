package com.airport.controller;



import com.airport.controller.request.change.PassportUpdateRequest;
import com.airport.controller.request.create.PassportSaveRequest;
import com.airport.entity.Passports;
import com.airport.entity.Status;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.repository.springdata.PassportsRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping(value = "/rest/passports")
@RequiredArgsConstructor
public class PassportsController {

	private final PassportsRepository passportsRepository;
	private final PassengersRepository passengersRepository;
	private final ConversionService conversionService;

	@ApiOperation(value = "Find all passports")
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
	public ResponseEntity<Page<Passports>> findAllPassports (@ApiIgnore Pageable pageable) {
		return new ResponseEntity<> (passportsRepository.findAll (pageable), HttpStatus.OK);
	}

	@ApiOperation(value = "Find passport by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Passports> findPassportById (@PathVariable("id") String id) {
		Passports passports = passportsRepository.findById (Long.valueOf (id))
		                                         .orElseThrow (() -> new EntityNotFoundException (Passports.class, id));
		return new ResponseEntity<> (passports, HttpStatus.OK);
	}

	@ApiOperation(value = "Find passport by id of passenger")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@GetMapping(value = "/passenger/{id}")
	public ResponseEntity<List<Passports>> findPassportsByPassengersId (@ApiParam(value = "Id of passenger") @PathVariable("id") String passengerId) {
		List<Passports> passports =passportsRepository.findByPassengerId_Id (Long.valueOf (passengerId))
		                                      .orElseThrow (() -> new EntityNotFoundException (Passports.class, passengerId));
		return new ResponseEntity<> (passports, HttpStatus.OK);
	}

	@ApiOperation(value = "Hard delete passport by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@DeleteMapping(value = "/hardDelete/{id}")
	public String hardDeletePassport (@PathVariable("id") String id) {
		Passports passports = passportsRepository.findById (Long.valueOf (id))
		                                         .orElseThrow (() -> new EntityNotFoundException (Passports.class, id));
		passportsRepository.delete (passports);
		return id;
	}

	@ApiOperation(value = "Safe delete passport by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@DeleteMapping(value = "/{id}")
	@Transactional (rollbackFor = Exception.class)
	public ResponseEntity<Passports> safeDeletePassport (@PathVariable("id") String id) {
		Passports passports = passportsRepository.findById (Long.valueOf (id))
		                                         .orElseThrow (() -> new EntityNotFoundException (Passports.class, id));
		passports.setStatus (Status.DELETED);
		return ResponseEntity.ok (passportsRepository.saveAndFlush (passports));
	}

	@ApiOperation(value = "Delete all passports of passenger by passenger id ")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@Transactional (rollbackFor = Exception.class)
	@DeleteMapping(value = "/passenger/{id}")
	public ResponseEntity<Long> deletePassportByPassengersId (@PathVariable("id") String passengerId) {
		return ResponseEntity.ok (passportsRepository.deleteByPassengerId_Id (Long.valueOf (passengerId)));
	}

	@ApiOperation(value = "Save new passport")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@PostMapping
	@Transactional (rollbackFor = Exception.class)
	public ResponseEntity<Passports> createPassport (@PathVariable String passengerId,
	                                                 @RequestBody @Valid PassportSaveRequest passportSaveRequest) {
		passportSaveRequest.setPassengerId (Long.valueOf (passengerId));
		return new ResponseEntity<> (passportsRepository.saveAndFlush (Objects.requireNonNull (
				conversionService.convert (passportSaveRequest, Passports.class))), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update passport by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@PutMapping(value = "/{id}")
	@Transactional (rollbackFor = Exception.class)
	public ResponseEntity<Passports> updatePassport (@PathVariable String id, @RequestBody @Valid PassportUpdateRequest passportUpdateRequest) {
		passportUpdateRequest.setId (id);
		return new ResponseEntity<> (passportsRepository.saveAndFlush (Objects.requireNonNull (
				conversionService.convert (passportUpdateRequest, Passports.class))), HttpStatus.CREATED);
	}
}
