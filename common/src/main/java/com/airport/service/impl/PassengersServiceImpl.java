package com.airport.service.impl;

import com.airport.entity.Passenger;
import com.airport.entity.Passports;
import com.airport.entity.Status;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.repository.springdata.PassportsRepository;
import com.airport.repository.springdata.RoleRepository;
import com.airport.service.PassengersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassengersServiceImpl implements PassengersService {

	final RoleRepository roleRepository;
	private final PassengersRepository passengersRepository;
	private final PassportsRepository passportsRepository;
	private final MailSenderService mailSenderService;

	@Override
	@Transactional
	public Passenger saveAndUpdate (Passenger passenger) {

		Set<Passports> passportsSet = passenger.getPassports ();

		passenger.setPassports (Collections.emptySet ());

		Passenger thisPassenger = passengersRepository.save (passenger);

		thisPassenger.setPassports (passportsSet.stream ()
		                                        .peek (s -> {
			                                        s.setPassengerId (thisPassenger);
			                                        passportsRepository.save (s);
		                                        })
		                                        .collect (Collectors.toSet ()));
		if (!thisPassenger.getStatus ()
		                 .equals (Status.ACTIVE)) {
			thisPassenger.setActivationCode (UUID.randomUUID ()
			                                     .toString ());

			String message = String.format ("Hello, %S! \n" + "Visit next link http://localhost:8080/registration/activate/%s",
					passenger.getName (),
					passenger.getActivationCode ());

			mailSenderService.sendEmail (passenger.getLogin (), "Activation", message);
		}
		passengersRepository.flush ();
		return thisPassenger;
	}

	@Override
	public String activate (String code) {
		Optional<Passenger> passenger = passengersRepository.findByActivationCode (code);
		if (passenger.isPresent ()) {
			passenger.get ()
			         .setStatus (Status.ACTIVE);
			passenger.get ()
			         .setActivationCode (null);
			passengersRepository.flush ();
			return String.format ("%s, thx for registration",
					passenger.get ()
					         .getName ());
		}
		return "invalid activation code";
	}
}

