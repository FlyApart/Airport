package com.airline.util.converters;



import com.airline.controller.request.PassengerSaveRequest;
import com.airline.controller.request.PassengerUpdateRequest;
import com.airline.entity.Passengers;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConverterRequestPassenger implements Converter <PassengerSaveRequest, Passengers> {

	@Override
	public Passengers convert (PassengerSaveRequest entity) {

		Passengers passengers = new Passengers ();
		passengers.setName (entity.getName ());
		passengers.setSecondName (entity.getSecondName ());
		passengers.setPassword (entity.getPassword ());
		passengers.setBirthDate (entity.getBirthDate ());
		passengers.setLogin (entity.getLogin ());

		return passengers;
	}

	public Passengers convert (PassengerUpdateRequest entity, Passengers passengers) {

		passengers.setName (entity.getName ());
		passengers.setSecondName (entity.getSecondName ());
		passengers.setPassword (entity.getPassword ());
		passengers.setBirthDate (entity.getBirthDate ());


		return passengers;
	}

}
		//passengers.setCreated (new Timestamp (System.currentTimeMillis ()));
		//passengers.setCountry (countryDao.findByName (entity.getPassengerMainInfo ().getCountry ()));
