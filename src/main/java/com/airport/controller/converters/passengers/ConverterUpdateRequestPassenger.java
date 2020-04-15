package com.airport.controller.converters.passengers;

import com.airport.controller.converters.passports.ConverterUpdateRequestPassports;
import com.airport.controller.request.change.PassengerUpdateRequest;
import com.airport.controller.request.change.PassportUpdateRequest;
import com.airport.entity.Passenger;
import com.airport.entity.Passports;
import com.airport.repository.springdata.CitiesRepository;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.repository.springdata.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ConverterUpdateRequestPassenger extends ConverterRequestPassengers<PassengerUpdateRequest, Passenger> {
	@Autowired
	private ConverterUpdateRequestPassports converterUpdateRequestPassports;

	public ConverterUpdateRequestPassenger (BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository, CitiesRepository citiesRepository, PassengersRepository passengersRepository) {
		super (passwordEncoder, roleRepository, citiesRepository, passengersRepository);
	}

	@Override
	public Passenger convert (PassengerUpdateRequest request) {

		Passenger passenger = findPassengerById (request.getClass (),Long.valueOf (request.getId ()));

		Set<Passports> passportsSet = new HashSet<> ();
		if (request.getPassportUpdateRequest () != null) {
			for (PassportUpdateRequest passportUpdateRequest : request.getPassportUpdateRequest ()) {
				passportUpdateRequest.setPassengerId (request.getId ());
				passportsSet.add (converterUpdateRequestPassports.convert (passportUpdateRequest));
			}
		}
		passenger.setPassports (passportsSet);

		if (request.getCities () != null) {
			passenger.setCities (findCity (request.getClass (), request.getCities ()));
		}

		return doConvert (passenger, request);

	}
}