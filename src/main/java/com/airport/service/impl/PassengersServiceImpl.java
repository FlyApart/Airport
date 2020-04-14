package com.airport.service.impl;

import com.airport.entity.Passengers;
import com.airport.entity.Passports;
import com.airport.entity.Role;
import com.airport.entity.RoleName;
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
	private final RoleRepository roleRepository;


	@Override
	@Transactional
	public Passengers saveAndUpdate (Passengers passenger) {
		Role role = new Role ();
		Set<Passports> passportsSet = passenger.getPassports ();
		passenger.setPassports (null);
		if (passenger.getRole () == null) {
			role.setRole (RoleName.USER);
		}
		Passengers thisPassenger = passengersRepository.saveAndFlush (passenger);
		for (Passports passports : passportsSet) {
			passports.setPassengersId (thisPassenger);
			passportsRepository.saveAndFlush (passports);
		}
		if (role.getRole () != null) {
			role.setPassengerId (thisPassenger);
			roleRepository.saveAndFlush (role);
		}
		thisPassenger.setRole (role);
		thisPassenger.setPassports (passportsSet);
		return thisPassenger;
	}


}

