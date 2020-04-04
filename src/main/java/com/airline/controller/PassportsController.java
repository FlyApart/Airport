package com.airline.controller;



import com.airline.controller.request.PassportSaveRequest;
import com.airline.controller.request.PassportUpdateRequest;
import com.airline.entity.Passports;
import com.airline.repository.PassengerDao;
import com.airline.repository.PassportDao;
import com.airline.repository.springdata.PassportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/rest/passports")
@RequiredArgsConstructor
public class PassportsController {

	private final PassportDao passportDao;
	private final PassengerDao passengerDao;
	private final PassportsRepository passportsRepository;
	private final ConversionService conversionService;

	@GetMapping
	public ResponseEntity<List<Passports>> getPassengers() {
		ResponseEntity <List<Passports>> response = new ResponseEntity<>(passportDao.findAll(), HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "/passport/{passengerId}")
	public ResponseEntity <List<Passports>> findPassportsByPassengersId (@PathVariable ("passengerId") Long passengerId){

		return new ResponseEntity<>(passportDao.findByPassengersId (passengerId), HttpStatus.OK);
	}

    @GetMapping(value = "/{id}")
    public ResponseEntity <Passports> findPassportsById (@PathVariable ("id") Long id){
        return new ResponseEntity<>(passportsRepository.findById (id).get (), HttpStatus.OK);
    }


	@DeleteMapping(value = "/{passengerId}")
	public Long DeletePassportsByPassengersId (@PathVariable ("passengerId") Long passengerId){
		passportDao.delete (passengerId);
		return passengerId;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Passports> createPassport (@RequestBody @Valid PassportSaveRequest passportSaveRequest) {

		return new ResponseEntity<> (passportsRepository.save (conversionService.convert (passportSaveRequest,Passports.class)), HttpStatus.CREATED);
	}

	@PutMapping (value = "/passport/{passengerId}")
	@Transactional
	public ResponseEntity<Passports> updatePassport (@PathVariable ("passengerId") Long passengerId, @RequestBody @Valid PassportUpdateRequest passportUpdateRequest) {
		return new ResponseEntity<> (passportsRepository.saveAndFlush (conversionService.convert (passportUpdateRequest, Passports.class)), HttpStatus.CREATED);
	}


}
