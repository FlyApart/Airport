package com.airline.util.converters.parent;

import com.airline.controller.request.PassengerSaveRequest;
import com.airline.entity.Countries;
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
