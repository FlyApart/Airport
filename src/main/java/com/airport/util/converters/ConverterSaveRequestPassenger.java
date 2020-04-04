package com.airport.util.converters;

import com.airport.controller.request.save.PassengerSaveRequest;
import com.airport.controller.request.save.PassportSaveRequest;
import com.airport.entity.Passengers;
import com.airport.entity.Passports;
import com.airport.util.converters.parent.ConverterRequestPassengers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
//TODO @EnableJpaRepositories 13.45
public class ConverterSaveRequestPassenger extends ConverterRequestPassengers<PassengerSaveRequest, Passengers> {

	@Autowired
	private ConverterSaveRequestPassports converterSaveRequestPassport;

	public ConverterSaveRequestPassenger (BCryptPasswordEncoder passwordEncoder) {
		super (passwordEncoder);
	}

	@Override
	public Passengers convert (PassengerSaveRequest request) {

		Passengers passengers = new Passengers ();

		Set<Passports> passportsSet = new HashSet<> ();
		for (PassportSaveRequest p : request.getPassportSaveRequest ()) {
			passportsSet.add (converterSaveRequestPassport.convert (p));
		}
		//passengers.setPassports (passportsSet);
		//passengers.setCountries (new Countries ().builder ().name (request.getCountry ()).build ());

		return doConvert (passengers, request);
	}
}

