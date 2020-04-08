package com.airport.util.converters.passengers;

import com.airport.controller.request.change.PassengerUpdateRequest;
import com.airport.controller.request.create.PassengerSaveRequest;
import com.airport.entity.Passengers;
import com.airport.util.CurrentTime;
import com.airport.util.converters.EntityConverter;
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
		passengers.setCreated (new CurrentTime ().getCurrentTime ());
		passengers.setLogin (entity.getLogin ());
		return passengers;
	}

	protected Passengers doConvert (Passengers passengers, PassengerUpdateRequest entity) {
		if(entity.getId()!=null)passengers.setId (Long.valueOf(entity.getId ()));
		if(entity.getName()!=null)passengers.setName (entity.getName ());
		if(entity.getSecondName()!=null)passengers.setSecondName (entity.getSecondName ());
		if(entity.getPassword()!=null)passengers.setPassword (passwordEncoder.encode (entity.getPassword ()));
		if(entity.getBirthDate()!=null)passengers.setBirthDate (entity.getBirthDate ());
		passengers.setChanged (new CurrentTime ().getCurrentTime ());
		//passengers.setLogin (entity.getLogin ());
		return passengers;
	}

}
