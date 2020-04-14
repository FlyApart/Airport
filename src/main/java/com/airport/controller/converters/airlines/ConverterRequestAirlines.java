package com.airport.controller.converters.airlines;

import com.airport.controller.converters.EntityConverter;
import com.airport.controller.request.change.AirlinesUpdateRequest;
import com.airport.controller.request.create.AirlinesSaveRequest;
import com.airport.entity.Airlines;
import com.airport.entity.Countries;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityAlreadyExistException;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.AirlinesRepository;
import com.airport.repository.springdata.CountriesRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ConverterRequestAirlines<S, T> extends EntityConverter<S, T> {

	private final CountriesRepository countriesRepository;
	private final AirlinesRepository airlinesRepository;

	protected Airlines doConvert (Airlines airlines, AirlinesSaveRequest entity) {
		airlines.setName (entity.getName ());
		airlines.setFleet (Long.valueOf (entity.getSumFleet ()));
		airlines.setFlightsCounts (Long.valueOf (entity.getFlightsCounts ()));
		airlines.setWebsite (entity.getWebsite ());
		return airlines;
	}

	protected Airlines doConvert (Airlines airlines, AirlinesUpdateRequest entity) {
		if (entity.getName () != null) airlines.setName (entity.getName ());
		if (entity.getSumFleet () != null) airlines.setFleet (Long.valueOf (entity.getSumFleet ()));
		if (entity.getFlightsCounts () != null) airlines.setFlightsCounts (Long.valueOf (entity.getFlightsCounts ()));
		if (entity.getWebsite () != null) airlines.setWebsite (entity.getWebsite ());
		return airlines;
	}

	Countries findCountries (Class<?> sClass, String country) {
		return countriesRepository.findByName (country)
		                          .orElseThrow (() -> new ConversionException (sClass, Airlines.class, country, new EntityNotFoundException  (" name = " + country, Countries.class)));
	}


	void isUniqueAirlines (Class<?> sClass, String name, String website) {

		boolean unique = airlinesRepository.findByNameAndWebsite (name, website)
		                                    .isPresent ();
		if (unique){
			throw new ConversionException (sClass, Airlines.class, name.concat (" " + website),
					new EntityAlreadyExistException (Airlines.class, "name = " + name + " or website = " + website));
		}
		else
			return;


	}
}
