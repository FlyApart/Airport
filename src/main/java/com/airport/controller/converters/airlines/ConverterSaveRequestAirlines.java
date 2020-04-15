package com.airport.controller.converters.airlines;

import com.airport.controller.request.create.AirlinesSaveRequest;
import com.airport.entity.Airline;
import com.airport.repository.springdata.AirlinesRepository;
import com.airport.repository.springdata.CountriesRepository;
import org.springframework.stereotype.Component;

@Component
public class ConverterSaveRequestAirlines extends ConverterRequestAirlines<AirlinesSaveRequest, Airline> {


	public ConverterSaveRequestAirlines (CountriesRepository countriesRepository, AirlinesRepository airlinesRepository) {
		super (countriesRepository, airlinesRepository);
	}

	@Override
	public Airline convert (AirlinesSaveRequest request) {

		Airline airline = new Airline ();

		airline.setCountries (findCountries (request.getClass (), request.getCountry ()));

		isUniqueAirlines (request.getClass (), request.getName (), request.getWebsite ());

		return doConvert (airline, request);
	}
}
