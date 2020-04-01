package com.airline.controller;

import com.airline.controller.request.PassengerSaveRequest;
import com.airline.controller.request.PassengerUpdateRequest;
import com.airline.entity.Passengers;
import com.airline.repository.PassengerDao;
import com.airline.repository.springdata.PassengerRepository;
import com.airline.service.PassengersService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/rest/passengers")
@RequiredArgsConstructor
public class PassengersController {

	private final PassengerDao passengersDao;

	private final PassengerRepository passengerRepository;

	private final PassengersService passengersService;

	private final ConversionService conversionService;

	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
					value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
					value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
					value = "Sorting criteria in the format: property(, \"asc or desc\"). " +
							        "Default sort order is ascending. " +
							        "Multiple sort criteria are supported.")
	})
	@GetMapping
	public ResponseEntity<Page<Passengers>> getAllPassengers (@ApiIgnore Pageable pageable) {
		passengerRepository.findAll();
		return new ResponseEntity<>(passengerRepository.findAll (pageable), HttpStatus.OK);
	}


	/*@GetMapping(value = "/{id}")
	public ResponseEntity<Passengers> findPassengerById (@PathVariable String id) {
		Passengers passengers = passengerRepository.findById(Long.valueOf(id)).orElseThrow(() -> new EntityNotFoundException (Passengers.class, id));
		return new ResponseEntity<> (passengers, HttpStatus.OK);
	}*/

	@PostMapping
	@Transactional                              //  TODO add (rollbackFor =)
	public ResponseEntity<Passengers> createPassenger (@RequestBody @Valid PassengerSaveRequest passengerInfo) {
		//        roleDao.save(new Role(savedUser.getUserId(), "ROLE_USER"));

		return new ResponseEntity<> (passengersService.save (conversionService.convert(passengerInfo, Passengers.class)), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Passengers> updatePassenger (@ApiParam(value = "User ID", required = true) @PathVariable ("id") Long id, @RequestBody @Valid PassengerUpdateRequest passengerInfo) {
		return new ResponseEntity<> (passengersService.save (conversionService.convert(passengerInfo, Passengers.class)), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Long> deletePassenger (@PathVariable("id") Long id) {
		passengerRepository.deletePassengersById (id);
		return new ResponseEntity<> (id, HttpStatus.OK);
	}




	/*@PostMapping
	@Transactional
	public ResponseEntity<TestUser> createUser(@RequestBody @Valid UserCreateRequest request) {
		TestUser convertedUser = conversionService.convert(request, TestUser.class);
		return new ResponseEntity<>(userRepository.saveAndFlush(convertedUser), CREATED);
	}


	public ResponseEntity<TestUser> updateUser(@RequestBody @Valid UserUpdateRequest request) {
		return new ResponseEntity<>(userRepository.save(conversionService.convert(request, TestUser.class)), HttpStatus.OK);
	}*/
}
