package com.airport.controller.converters.cities;

import com.airport.controller.request.change.CitiesUpdateRequest;
import com.airport.entity.Cities;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.CitiesRepository;
import com.airport.repository.springdata.CountriesRepository;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class ConverterUpdateRequestCities extends ConverterRequestCities<CitiesUpdateRequest, Cities> {



	public ConverterUpdateRequestCities (CitiesRepository citiesRepository, CountriesRepository countriesRepository) {
		super (citiesRepository, countriesRepository);
	}

	@Override
	public Cities convert (CitiesUpdateRequest request) {
		Cities cities = ofNullable (entityManager.find (Cities.class, Long.valueOf (request.getId ())))
				                .orElseThrow (() -> new ConversionException (CitiesUpdateRequest.class, Cities.class, request, new EntityNotFoundException (Cities.class, request.getId ())));

		uniqueCitiesName (request.getClass (), request.getName ());

		cities.setCountries (findCountries (request.getClass (), request.getCountry ()));

		return doConvert (cities, request);
	}
}
