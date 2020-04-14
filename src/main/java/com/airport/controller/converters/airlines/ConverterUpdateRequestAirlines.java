package com.airport.controller.converters.airlines;

import com.airport.controller.request.change.AirlinesUpdateRequest;
import com.airport.entity.Airlines;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.AirlinesRepository;
import com.airport.repository.springdata.CountriesRepository;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class ConverterUpdateRequestAirlines extends ConverterRequestAirlines<AirlinesUpdateRequest, Airlines> {



	public ConverterUpdateRequestAirlines (CountriesRepository countriesRepository, AirlinesRepository airlinesRepository) {
		super (countriesRepository, airlinesRepository);
	}

	@Override
	public Airlines convert (AirlinesUpdateRequest request) {

		Airlines airlines = ofNullable (entityManager.find (Airlines.class, Long.valueOf (request.getId ()))).orElseThrow (() -> new ConversionException (AirlinesUpdateRequest.class, Airlines.class, request, new EntityNotFoundException (Airlines.class, request.getId ())));

		isUniqueAirlines (request.getClass (), request.getName (), request.getWebsite ());

		airlines.setCountries (findCountries (request.getClass (), request.getCountry ()));

		return doConvert (airlines, request);
	}
}
