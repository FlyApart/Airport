package com.airport.service.impl;

import com.airport.entity.Passengers;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.repository.springdata.PassportsRepository;
import com.airport.repository.springdata.RoleRepository;
import com.airport.service.PassengersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PassengersServiceImpl implements PassengersService {

	private final PassengersRepository passengersRepository;
	private final PassportsRepository passportsRepository;
	private final RoleRepository roleRepository;


	@Override
	@Transactional
	public Passengers saveAndUpdate (Passengers passenger) {
		/*Set<Role> role= Collections.emptySet ();
		Set<Passports> passportsSet = passenger.getPassports ();
		passenger.setPassports (null);

		if (passenger.getRole () == null) {
			role.add (new Role (RoleName.USER));
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
		thisPassenger.setRole ();
		thisPassenger.setPassports (passportsSet);
		return thisPassenger;*/
		return null;
	}


}

