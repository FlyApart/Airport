package com.airport.controller.converters.cities;

import com.airport.controller.converters.EntityConverter;
import com.airport.controller.request.change.CitiesUpdateRequest;
import com.airport.controller.request.create.CitiesSaveRequest;
import com.airport.entity.Cities;
import com.airport.entity.Countries;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityAlreadyExistException;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.CitiesRepository;
import com.airport.repository.springdata.CountriesRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ConverterRequestCities<S, T> extends EntityConverter<S, T> {

	private final CitiesRepository citiesRepository;
	private final CountriesRepository countriesRepository;

	protected Cities doConvert (Cities cities, CitiesSaveRequest entity) {
		cities.setName (entity.getName ());
		return cities;
	}

	protected Cities doConvert (Cities cities, CitiesUpdateRequest entity) {
		if (entity.getName () != null) cities.setName (entity.getName ());
		return cities;
	}

	Countries findCountries (Class<?> sClass, String name) {
		return countriesRepository.findByName (name)
		                          .orElseThrow (() -> new ConversionException (sClass, Cities.class, name, new EntityNotFoundException (" name = " + name, Countries.class)));
	}

	void uniqueCitiesName (Class<?> sClass, String name) {
		boolean unique = citiesRepository.findByName (name)
		                                   .isPresent ();
		if (unique){
			throw new ConversionException (sClass, Cities.class, name, new EntityAlreadyExistException (" name = " + name));
		}
		else
			return;
	}
}
