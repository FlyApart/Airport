package com.airport.util.converters.parent;

import com.airport.controller.request.change.PassengerUpdateRequest;
import com.airport.controller.request.create.PassengerSaveRequest;
import com.airport.entity.Passengers;
import com.airport.util.CurrentTime;
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
		passengers.setCreated(new CurrentTime ().getCurrentTime ());
		return passengers;
	}

	protected Passengers doConvert (Passengers passengers, PassengerUpdateRequest entity) {
		if(entity.getName() !=null)passengers.setName (entity.getName ());
		if(entity.getSecondName() !=null)passengers.setSecondName (entity.getSecondName ());
		if(entity.getPassword() !=null)passengers.setPassword (passwordEncoder.encode (entity.getPassword ()));
		if(entity.getBirthDate() !=null)passengers.setBirthDate (entity.getBirthDate ());
		passengers.setChanged(new CurrentTime ().getCurrentTime ());
		return passengers;
	}
}
