package com.airport.controller;



import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.change.PassportUpdateRequest;
import com.airport.controller.request.create.PassportSaveRequest;
import com.airport.entity.Passengers;
import com.airport.entity.Passports;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.repository.springdata.PassportsRepository;
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
import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping(value = "/rest/passports")
@RequiredArgsConstructor
public class PassportsController {

	private final PassportsRepository passportsRepository;
	private final PassengersRepository passengersRepository;
	private final ConversionService conversionService;

	@ApiImplicitParams(
			{@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(, " +
					          "\"asc or desc\"). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
	@GetMapping
	public ResponseEntity<Page<Passports>> findAllPassports(@ApiIgnore Pageable pageable) {
		ResponseEntity <Page<Passports>> response = new ResponseEntity<>(passportsRepository.findAll(pageable), HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity <Passports> findPassportById (@PathVariable ("id") String id){
		Passports passports = passportsRepository.findById (Long.valueOf (id)).orElseThrow (() -> new EntityNotFoundException (Passports.class, id));
		return new ResponseEntity<>(passports, HttpStatus.OK);
	}

    @GetMapping(value = "/passenger/{passengerId}")
    public ResponseEntity <Set<Passports>> findPassportsByPassengersId (@PathVariable ("passengerId") String passengerId){
	    Passengers passengers =  passengersRepository.findById (Long.valueOf (passengerId)).orElseThrow  (() -> new EntityNotFoundException (Passengers.class, passengerId));
	    return new ResponseEntity<>(passengers.getPassports (), HttpStatus.OK);
    }

	@Transactional
	@DeleteMapping(value = "/{id}")
	public String DeletePassport (@PathVariable ("id") String id){
		Passports passports = passportsRepository.findById (Long.valueOf (id))
		                                         .orElseThrow(() -> new EntityNotFoundException (Passports.class, id));
		passportsRepository.delete (passports);
		return id;
	}

	@Transactional
	@DeleteMapping(value = "/passenger/{passengerId}")
	public String DeletePassportByPassengersId (@PathVariable ("passengerId") String passengerId){

		Set<Passports> passports = Optional.ofNullable (passengersRepository.findPassportsByPassportsId(Long.valueOf (passengerId)))
		                                                       .orElseThrow(() -> new EntityNotFoundException (Passports.class, passengerId));
		passportsRepository.deletePassports (passports);
		return passengerId;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Passports> createPassport (@PathVariable String passengerId,
	                                                 @RequestBody @Valid PassportSaveRequest passportSaveRequest) {
		passportSaveRequest.setPassengerId (passengerId);
		Passports passports = conversionService.convert (passportSaveRequest,Passports.class);
		return new ResponseEntity<> (passportsRepository.saveAndFlush (passports), HttpStatus.CREATED);
	}

	@PutMapping (value = "/{id}")
	@Transactional
	public ResponseEntity<Passports> updatePassport (@PathVariable String id, @RequestBody @Valid PassportUpdateRequest passportUpdateRequest) {
		passportUpdateRequest.setId (id);
		Passports passports = conversionService.convert (passportUpdateRequest, Passports.class);
		return new ResponseEntity<> (passportsRepository.saveAndFlush (passports), HttpStatus.CREATED);
	}


}
