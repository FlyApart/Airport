package com.airport.controller.converters.airports;

import com.airport.controller.converters.EntityConverter;
import com.airport.controller.request.change.AirportsUpdateRequest;
import com.airport.controller.request.create.AirportsSaveRequest;
import com.airport.entity.Airports;
import com.airport.entity.Cities;
import com.airport.entity.Countries;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityAlreadyExistException;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.AirportsRepository;
import com.airport.repository.springdata.CitiesRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ConverterRequestAirports<S, T> extends EntityConverter<S, T> {

	private final CitiesRepository citiesRepository;
	private final AirportsRepository airportsRepository;

	protected Airports doConvert (Airports airports, AirportsSaveRequest entity) {
		airports.setTitle (entity.getTitle ());
		return airports;
	}

	protected Airports doConvert (Airports airports, AirportsUpdateRequest entity) {
		if (entity.getTitle () != null) airports.setTitle (entity.getTitle ());
		return airports;
	}

	Cities findCity (Class<?> sClass, String city) {
		return citiesRepository.findByName (city)
		                       .orElseThrow (() -> new ConversionException (sClass, Airports.class, city, new EntityNotFoundException (" Cities = " + city, Countries.class)));
	}


	void isUniqueAirports (Class<?> sClass, String title) {
		boolean unique = airportsRepository.findByTitle (title)
		                                   .isPresent ();
		if (unique){
			throw new ConversionException (sClass, Airports.class, title, new EntityAlreadyExistException (Airports.class, "title = " + title));
		}
		else
			return;
	}
}
