package com.airport.util.converters.parent;

import com.airport.controller.request.save.PassengerSaveRequest;
import com.airport.entity.Countries;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//TODO add
public abstract class ConverterRequestCountries<S, T> extends EntityConverter<S, T> {

	protected Countries doConvert (Countries countries, PassengerSaveRequest entity) {

		return countries;
	}


	protected Countries doConvert (Countries countries, String name) {
		countries.setName (name);
		return countries;
	}
}
