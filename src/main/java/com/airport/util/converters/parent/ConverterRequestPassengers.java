package com.airport.util.converters.parent;

import com.airport.controller.request.change.PassengerUpdateRequest;
import com.airport.controller.request.create.PassengerSaveRequest;
import com.airport.entity.Passengers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public abstract class ConverterRequestPassengers<S, T> extends EntityConverter<S, T> {

	private final BCryptPasswordEncoder passwordEncoder;

	protected Passengers doConvert (Passengers passengers, PassengerSaveRequest entity) {
		passengers.setName (entity.getName ());
		passengers.setSecondName (entity.getSecondName ());
		passengers.setPassword (passwordEncoder.encode (entity.getPassword ()));
		passengers.setBirthDate (entity.getBirthDate ());
		passengers.setLogin (entity.getLogin ());
		return passengers;
	}

	protected Passengers doConvert (Passengers passengers, PassengerUpdateRequest entity) {
		passengers.setId (Long.valueOf(entity.getId ()));
		passengers.setName (entity.getName ());
		passengers.setSecondName (entity.getSecondName ());
		passengers.setPassword (passwordEncoder.encode (entity.getPassword ()));
		passengers.setBirthDate (entity.getBirthDate ());
		//passengers.setLogin (entity.getLogin ());
		return passengers;
	}

}
