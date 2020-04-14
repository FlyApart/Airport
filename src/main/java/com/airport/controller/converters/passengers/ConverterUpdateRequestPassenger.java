package com.airport.controller.converters.passengers;

import com.airport.controller.converters.passports.ConverterUpdateRequestPassports;
import com.airport.controller.request.change.PassengerUpdateRequest;
import com.airport.controller.request.change.PassportUpdateRequest;
import com.airport.entity.Passengers;
import com.airport.entity.Passports;
import com.airport.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static java.util.Optional.ofNullable;

@Component
public class ConverterUpdateRequestPassenger extends ConverterRequestPassengers<PassengerUpdateRequest, Passengers> {
	@Autowired
	private ConverterUpdateRequestPassports converterUpdateRequestPassports;

	public ConverterUpdateRequestPassenger (BCryptPasswordEncoder passwordEncoder) {
		super (passwordEncoder);
	}


	@Override
	public Passengers convert (PassengerUpdateRequest request) {

		Passengers passengers = ofNullable (entityManager.find (Passengers.class, Long.valueOf (request.getId ()))).orElseThrow (() -> new EntityNotFoundException (Passengers.class, request.getId ()));


		Set<Passports> passportsSet = new HashSet<> ();
		if (request.getPassportUpdateRequest () != null) {
			for (PassportUpdateRequest passportUpdateRequest : request.getPassportUpdateRequest ()) {
				passportUpdateRequest.setPassengerId (request.getId ());
				passportsSet.add (converterUpdateRequestPassports.convert (passportUpdateRequest));
			}
		}
		passengers.setPassports (passportsSet);

		if (request.getCities () != null) passengers.setCities (findCity (request.getClass (), request.getCities ()));
		return doConvert (passengers, request);

	}
}