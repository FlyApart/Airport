package com.airline.controller;



import com.airline.controller.request.PassportRequest;
import com.airline.entity.Passports;
import com.airline.repository.PassengerDao;
import com.airline.repository.PassportDao;
import lombok.RequiredArgsConstructor;
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

	@GetMapping
	public ResponseEntity<List<Passports>> getPassengers() {
		ResponseEntity <List<Passports>> response = new ResponseEntity<>(passportDao.findAll(), HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "/passenger/{passengerId}")
	public ResponseEntity <List<Passports>> findPassportsByPassengersId (@PathVariable ("passengerId") Long passengerId){

		return new ResponseEntity<>(passportDao.findByPassengersId (passengerId), HttpStatus.OK);
	}

    @GetMapping(value = "/{id}")
    public ResponseEntity <Passports> findPassportsById (@PathVariable ("id") Long id){
        return new ResponseEntity<>(passportDao.findById (id), HttpStatus.OK);
    }


	@DeleteMapping(value = "/{passengerId}")
	public Long DeletePassportsByPassengersId (@PathVariable ("passengerId") Long passengerId){
		passportDao.delete (passengerId);
		return passengerId;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Passports> createPassport (@RequestBody @Valid PassportRequest passportRequest) {
		//        roleDao.save(new Role(savedUser.getUserId(), "ROLE_USER"));
		Passports passports = new Passports ();
		passports.setSeries (passportRequest.getSeries ());
		passports.setNumber (passportRequest.getNumber ());
		passports.setTitle (passportRequest.getTitle ());
		passports.setPassengersId (passengerDao.findById (13L));
		return new ResponseEntity<> (passportDao.save (passports), HttpStatus.CREATED);
	}


}
