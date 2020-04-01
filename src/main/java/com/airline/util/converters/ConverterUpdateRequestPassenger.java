package com.airline.util.converters;

import com.airline.controller.request.PassengerUpdateRequest;
import com.airline.entity.Passengers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

import static java.util.Optional.ofNullable;

@Component
public class ConverterUpdateRequestPassenger extends ConverterRequestPassengers <PassengerUpdateRequest, Passengers> {

	public ConverterUpdateRequestPassenger (BCryptPasswordEncoder passwordEncoder) {
		super (passwordEncoder);
	}

	@Override
	public Passengers convert(PassengerUpdateRequest request) {

		Passengers passengers =
				ofNullable(entityManager.find(Passengers.class, request.getId ()))
						.orElseThrow(() -> new EntityNotFoundException ());
		//.orElseThrow(() -> new EntityNotFoundException(Passengers.class, request.getUserId()));
		return doConvert(passengers, request);
	}
}