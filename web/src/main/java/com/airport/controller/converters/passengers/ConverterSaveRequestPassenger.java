package com.airport.controller.converters.passengers;

import com.airport.controller.converters.passports.ConverterSaveRequestPassports;
import com.airport.controller.request.create.PassengerSaveRequest;
import com.airport.controller.request.create.PassportSaveRequest;
import com.airport.entity.Passengers;
import com.airport.entity.Passports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
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
			p.setPassengerId (null);
			passportsSet.add (converterSaveRequestPassport.convert (p));
		}
		passengers.setPassports (passportsSet);

		passengers.setCities (findCity(request.getClass(),request.getCities()));
        isUniqueLogin(request.getClass(), request.getLogin());

		return doConvert (passengers, request);
	}
}

