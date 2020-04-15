package com.airport.controller.converters.passengers;

import com.airport.controller.converters.passports.ConverterSaveRequestPassports;
import com.airport.controller.request.create.PassengerSaveRequest;
import com.airport.controller.request.create.PassportSaveRequest;
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
public class ConverterSaveRequestPassenger extends ConverterRequestPassengers<PassengerSaveRequest, Passenger> {

	@Autowired
	private ConverterSaveRequestPassports converterSaveRequestPassport;

	public ConverterSaveRequestPassenger (BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository, CitiesRepository citiesRepository, PassengersRepository passengersRepository) {
		super (passwordEncoder, roleRepository, citiesRepository, passengersRepository);
	}

	@Override
	public Passenger convert (PassengerSaveRequest request) {

		Passenger passenger = new Passenger ();

		Set<Passports> passportsSet = new HashSet<> ();
		for (PassportSaveRequest p : request.getPassportSaveRequest ()) {
			passportsSet.add (converterSaveRequestPassport.convert (p));
		}
		passenger.setPassports (passportsSet);

		passenger.setCities (findCity (request.getClass (), request.getCity ()));
		isUniqueLogin (request.getClass (), request.getLogin ());

		passenger.setRole (getRole ());

		return doConvert (passenger, request);
	}
}

