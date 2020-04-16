package com.airport.controller.converters.airlines;

import com.airport.controller.request.change.AirlinesUpdateRequest;
import com.airport.entity.Airline;
import com.airport.repository.springdata.AirlinesRepository;
import com.airport.repository.springdata.CountriesRepository;
import org.springframework.stereotype.Component;

@Component
public class ConverterUpdateRequestAirlines extends ConverterRequestAirlines<AirlinesUpdateRequest, Airline> {

	public ConverterUpdateRequestAirlines (CountriesRepository countriesRepository, AirlinesRepository airlinesRepository) {
		super (countriesRepository, airlinesRepository);
	}

	@Override
	public Airline convert (AirlinesUpdateRequest request) {

		Airline airline = findAirline(request.getClass (),Long.valueOf (request.getId ()));

		if (request.getName () != null && request.getWebsite () != null){
			isUniqueAirlines (request.getClass (), request.getName (), request.getWebsite ());
		}
		else if (request.getName () != null){
			isUniqueAirlines (request.getClass (), request.getName (), airline.getWebsite ());
		}
		else if (request.getWebsite () != null){
			isUniqueAirlines (request.getClass (), airline.getName (), request.getWebsite ());
		}

		if (request.getCountry ()!=null){
			airline.setCountries (findCountries (request.getClass (), request.getCountry ()));
		}

		return doConvert (airline, request);
	}
}
