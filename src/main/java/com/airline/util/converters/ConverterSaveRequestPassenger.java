package com.airline.util.converters;

import com.airline.controller.request.PassengerSaveRequest;
import com.airline.controller.request.PassportSaveRequest;
import com.airline.entity.Countries;
import com.airline.entity.Passengers;
import com.airline.entity.Passports;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
//@EnableJpaRepositories 13.45
public class ConverterSaveRequestPassenger extends ConverterRequestPassengers<PassengerSaveRequest, Passengers> {

	public ConverterSaveRequestPassenger (BCryptPasswordEncoder passwordEncoder) {
		super (passwordEncoder);
	}

	@Override
	public Passengers convert (PassengerSaveRequest request) {
		Passengers passengers = new Passengers ();
		Countries countries = new Countries ();

		Set<Passports> passportsSet = new HashSet<> ();
		for (PassportSaveRequest p : request.getPassportSaveRequestSet ()) {
			ConverterSaveRequestPassports converterSaveRequestPassport = new ConverterSaveRequestPassports ();
			passportsSet.add (converterSaveRequestPassport.convert (p));
		}
		passengers.setPassports (passportsSet);

		countries.setName (request.getCountry ());
		passengers.setCountries (countries);

		return doConvert (passengers, request);
	}
}

