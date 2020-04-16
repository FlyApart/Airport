package com.airport.service.impl;

import com.airport.entity.Passenger;
import com.airport.entity.Passports;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.repository.springdata.PassportsRepository;
import com.airport.repository.springdata.RoleRepository;
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

	final RoleRepository roleRepository;


	@Override
	@Transactional
	public Passenger saveAndUpdate (Passenger passenger) {

		Set<Passports> passportsSet = passenger.getPassports ();
		passenger.setPassports (null);

		Passenger thisPassenger = passengersRepository.saveAndFlush (passenger);
		for (Passports passports : passportsSet) {
			passports.setPassengerId (thisPassenger);
			passportsRepository.saveAndFlush (passports);
		}
		thisPassenger.setPassports (passportsSet);
		return thisPassenger;
	}


}
