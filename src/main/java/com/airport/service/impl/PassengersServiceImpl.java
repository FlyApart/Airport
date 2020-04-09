package com.airport.service.impl;

import com.airport.entity.Passengers;
import com.airport.entity.Passports;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.repository.springdata.PassportsRepository;
import com.airport.service.PassengersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PassengersServiceImpl implements PassengersService {

	private final PassengersRepository passengersRepository;
	private final PassportsRepository passportsRepository;


	@Override
    @Transactional
	public Passengers saveAndUpdate (Passengers passenger) {

		Set<Passports> passportsSet = passenger.getPassports ();
		passenger.setPassports (null);
		Passengers saveAndUpdate = passengersRepository.saveAndFlush (passenger);
		for (Passports passports : passportsSet) {
			passports.setPassengersId (saveAndUpdate);
			passportsRepository.saveAndFlush (passports);
		}
		return saveAndUpdate;
	}







}
