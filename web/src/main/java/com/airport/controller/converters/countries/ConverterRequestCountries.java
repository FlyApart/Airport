package com.airport.controller.converters.countries;

import com.airport.controller.converters.EntityConverter;
import com.airport.controller.request.change.CountriesUpdateRequest;
import com.airport.controller.request.create.CountriesSaveRequest;
import com.airport.entity.Countries;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityAlreadyExistException;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.CountriesRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ConverterRequestCountries<S, T> extends EntityConverter<S, T> {

	private final CountriesRepository countriesRepository;

	protected Countries doConvert (Countries countries, CountriesSaveRequest entity) {

		countries.setName (entity.getName ());
		countries.setPopulation (Long.valueOf (entity.getPopulation ()));
		return countries;
	}

	protected Countries doConvert (Countries countries, CountriesUpdateRequest entity) {

		if (entity.getName () != null) {
			countries.setName (entity.getName ());
		}

		if (entity.getPopulation () != null) {
			countries.setPopulation (Long.valueOf (entity.getPopulation ()));
		}

		return countries;
	}


	void isUniqueCountries (Class<?> sClass, String name) {

		boolean unique = countriesRepository.findByName (name)
		                                   .isPresent ();
		if (unique){
			throw new ConversionException (sClass, Countries.class, name,
					new EntityAlreadyExistException (Countries.class, " name = " +name));
		}
	}

	Countries findById (Class<?> sClass, Long id) {
		return countriesRepository.findById (id)
				            .orElseThrow (() -> new ConversionException (sClass, Countries.class, id, new EntityNotFoundException (Countries.class, id)));
	}

	Countries findCountriesByName (Class<?> sClass,String name) {
		return countriesRepository.findByName (name)
		                          .orElseThrow (() -> new ConversionException (sClass, Countries.class, name, new EntityNotFoundException (" name = " + name, Countries.class)));

	}


}
