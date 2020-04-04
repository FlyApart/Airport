package com.airline.util.converters;

import com.airline.controller.request.PassengerUpdateRequest;
import com.airline.controller.request.PassportUpdateRequest;
import com.airline.entity.Countries;
import com.airline.entity.Passengers;
import com.airline.entity.Passports;
import com.airline.util.converters.parent.ConverterRequestPassengers;
import com.airline.util.exceptions.EntityNotFoundException;
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
	public Passengers convert(PassengerUpdateRequest request) {

		Passengers passengers =
				ofNullable(entityManager.find(Passengers.class, Long.valueOf(request.getId ())))
						.orElseThrow(() -> new EntityNotFoundException (Passengers.class, request.getId ()));

		Set<Passports> passportsSet = new HashSet<> ();
		for (PassportUpdateRequest passportUpdateRequest : request.getPassportUpdateRequests ()) {
			passportUpdateRequest.setPassengerId (passengers.getId ().toString ());
			passportsSet.add (converterUpdateRequestPassports.convert (passportUpdateRequest));
		}

		passengers.setPassports (passportsSet);

		passengers.setCountries (ofNullable(entityManager.createQuery ("select c from Countries c where c.name = :name", Countries.class)
		                                 .setParameter ("name",request.getCountry ()).getSingleResult ())
				                         .orElseThrow (() -> new EntityNotFoundException (Passengers.class, request.getId ())));

		return doConvert(passengers, request);
	}
}