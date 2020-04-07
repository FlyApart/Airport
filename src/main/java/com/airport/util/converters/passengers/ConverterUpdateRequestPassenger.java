package com.airport.util.converters.passengers;

import com.airport.controller.exceptions.ConversionException;
import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.exceptions.MethodArgumentNotValidException;
import com.airport.controller.request.change.PassengerUpdateRequest;
import com.airport.controller.request.change.PassportUpdateRequest;
import com.airport.controller.request.create.PassengerSaveRequest;
import com.airport.entity.Cities;
import com.airport.entity.Passengers;
import com.airport.entity.Passports;
import com.airport.util.converters.passports.ConverterUpdateRequestPassports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
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

	Cities findCity (PassengerUpdateRequest request) {
		Cities cities;
		try {
			cities = entityManager.createQuery ("select c from Cities c where c.name=:name", Cities.class)
			                      .setParameter ("name", request.getName ())
			                      .getSingleResult ();
		} catch (NumberFormatException e) {
			throw new ConversionException (PassengerSaveRequest.class, Passengers.class, request, new MethodArgumentNotValidException (request.getCities ()));
		} catch (NoResultException e) {
			throw new ConversionException (PassengerSaveRequest.class, Passengers.class, request, new EntityNotFoundException ("City with name = " + request.getCities (), Passports.class));
		}
		return cities;
	}

	@Override
	public Passengers convert (PassengerUpdateRequest request) {

		Passengers passengers = ofNullable (entityManager.find (Passengers.class, Long.valueOf (request.getId ())))
				                        .orElseThrow (() -> new EntityNotFoundException (Passengers.class, request.getId ()));

		if (request.getPassportUpdateRequest () != null) {
			Set<Passports> passportsSet = new HashSet<> ();
			for (PassportUpdateRequest passportUpdateRequest : request.getPassportUpdateRequest ()) {
				passportsSet.add (converterUpdateRequestPassports.convert (passportUpdateRequest));
			}
			passengers.setPassports (passportsSet);
		}
		if (request.getCities () != null) passengers.setCities (findCity (request));
		return doConvert (passengers, request);
	}
}