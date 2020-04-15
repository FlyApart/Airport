package com.airport.controller.converters.airlines;

import com.airport.controller.converters.EntityConverter;
import com.airport.controller.request.change.AirlinesUpdateRequest;
import com.airport.controller.request.create.AirlinesSaveRequest;
import com.airport.entity.Airline;
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

	protected Airline doConvert (Airline airline, AirlinesSaveRequest entity) {
		airline.setName (entity.getName ());
		airline.setFleet (Long.valueOf (entity.getSumFleet ()));
		airline.setFlightsCounts (Long.valueOf (entity.getFlightsCounts ()));
		airline.setWebsite (entity.getWebsite ());
		return airline;
	}

	protected Airline doConvert (Airline airline, AirlinesUpdateRequest entity) {
		if (entity.getName () != null) {
			airline.setName (entity.getName ());
		}

		if (entity.getSumFleet () != null) {
			airline.setFleet (Long.valueOf (entity.getSumFleet ()));
		}

		if (entity.getFlightsCounts () != null) {
			airline.setFlightsCounts (Long.valueOf (entity.getFlightsCounts ()));
		}

		if (entity.getWebsite () != null) {
			airline.setWebsite (entity.getWebsite ());
		}

		return airline;
	}

	Countries findCountries (Class<?> sClass, String country) {
		return countriesRepository.findByName (country)
		                          .orElseThrow (() -> new ConversionException (sClass, Airline.class, country, new EntityNotFoundException  (" name = " + country, Countries.class)));
	}

	Airline findAirline(Class<?> sClass, Long id){
		return airlinesRepository.findById (id)
				.orElseThrow (()-> new ConversionException (sClass, Airline.class, id, new EntityNotFoundException (Airline.class, id)));
	}

	void isUniqueAirlines (Class<?> sClass, String name, String website) {

		boolean unique = airlinesRepository.findByNameAndWebsite (name, website)
		                                    .isPresent ();
		if (unique){
			throw new ConversionException (sClass, Airline.class, name.concat (" " + website),
					new EntityAlreadyExistException (Airline.class, "name = " + name + " or website = " + website));
		}
	}
}
