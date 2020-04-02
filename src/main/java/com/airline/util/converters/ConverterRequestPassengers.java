package com.airline.util.converters;

import com.airline.controller.request.PassengerSaveRequest;
import com.airline.entity.Passengers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;

@RequiredArgsConstructor
public abstract class ConverterRequestPassengers<S, T> extends EntityConverter<S, T> {

	private final BCryptPasswordEncoder passwordEncoder;

	protected Passengers doConvert (Passengers passengers, PassengerSaveRequest entity) {
		passengers.setName (entity.getName ());
		passengers.setSecondName (entity.getSecondName ());
		passengers.setPassword (passwordEncoder.encode (entity.getPassword ()));
		passengers.setBirthDate (entity.getBirthDate ());
		passengers.setLogin (entity.getLogin ());
		passengers.setCreated (new Timestamp (System.currentTimeMillis ()));
		return passengers;
	}
}
