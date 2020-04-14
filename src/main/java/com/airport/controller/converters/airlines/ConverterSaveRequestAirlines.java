package com.airport.controller.converters.airlines;

import com.airport.controller.request.create.AirlinesSaveRequest;
import com.airport.entity.Airlines;
import com.airport.repository.springdata.AirlinesRepository;
import com.airport.repository.springdata.CountriesRepository;
import org.springframework.stereotype.Component;

@Component
public class ConverterSaveRequestAirlines extends ConverterRequestAirlines<AirlinesSaveRequest, Airlines> {


	public ConverterSaveRequestAirlines (CountriesRepository countriesRepository, AirlinesRepository airlinesRepository) {
		super (countriesRepository, airlinesRepository);
	}

	@Override
	public Airlines convert (AirlinesSaveRequest request) {

		Airlines airlines = new Airlines ();

		airlines.setCountries (findCountries (request.getClass (), request.getCountry ()));

		isUniqueAirlines (request.getClass (), request.getName (), request.getWebsite ());

		return doConvert (airlines, request);
	}
}
