package com.airline.util.converters;

import com.airline.controller.request.PassengerSaveRequest;
import com.airline.entity.Countries;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ConverterRequestCountries<S, T> extends EntityConverter<S, T> {

	protected Countries doConvert (Countries countries, PassengerSaveRequest entity) {
		/*passengers.setName (entity.getName ());
		passengers.setSecondName (entity.getSecondName ());
		passengers.setPassword (passwordEncoder.encode (entity.getPassword ()));
		passengers.setBirthDate (entity.getBirthDate ());
		passengers.setLogin (entity.getLogin ());*/
		return countries;
	}
	protected Countries doConvert (Countries countries, String name) {
		countries.setName (name);
		return countries;
	}
}
