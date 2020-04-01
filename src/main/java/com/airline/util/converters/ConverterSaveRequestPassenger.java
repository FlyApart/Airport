package com.airline.util.converters;

import com.airline.controller.request.PassengerSaveRequest;
import com.airline.controller.request.PassportSaveRequest;
import com.airline.entity.Passengers;
import com.airline.entity.Passports;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component

public class ConverterSaveRequestPassenger extends ConverterRequestPassengers<PassengerSaveRequest, Passengers> {

	public ConverterSaveRequestPassenger (BCryptPasswordEncoder passwordEncoder) {
		super (passwordEncoder);
	}

	@Override
	public Passengers convert (PassengerSaveRequest request) {
		Passengers passengers = new Passengers ();

		Set<Passports> passportsSet = new HashSet<> ();
		for (PassportSaveRequest p : request.getPassportSaveRequestSet ()) {
			ConverterSaveRequestPassport converterSaveRequestPassport = new ConverterSaveRequestPassport ();
			passportsSet.add (converterSaveRequestPassport.convert (p));
		}
		passengers.setPassports (passportsSet);
		return doConvert (passengers, request);
	}
}

