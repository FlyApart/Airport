package com.airport.controller.converters.airports;

import com.airport.controller.request.create.AirportsSaveRequest;
import com.airport.entity.Airports;
import com.airport.repository.springdata.AirportsRepository;
import com.airport.repository.springdata.CitiesRepository;
import org.springframework.stereotype.Component;

@Component
public class ConverterSaveRequestAirports extends ConverterRequestAirports<AirportsSaveRequest, Airports> {

	public ConverterSaveRequestAirports (CitiesRepository citiesRepository, AirportsRepository airportsRepository) {
		super (citiesRepository, airportsRepository);
	}

	@Override
	public Airports convert (AirportsSaveRequest request) {

		Airports airports = new Airports ();

		airports.setCities (findCity (request.getClass (), request.getCities ()));

		isUniqueAirports (request.getClass (), request.getTitle ());

		return doConvert (airports, request);
	}
}
