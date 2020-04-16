package com.airport.controller.converters.airports;

import com.airport.controller.request.change.AirportsUpdateRequest;
import com.airport.entity.Airports;
import com.airport.repository.springdata.AirportsRepository;
import com.airport.repository.springdata.CitiesRepository;
import org.springframework.stereotype.Component;

@Component
public class ConverterUpdateRequestAirports extends ConverterRequestAirports<AirportsUpdateRequest, Airports> {

	public ConverterUpdateRequestAirports (CitiesRepository citiesRepository, AirportsRepository airportsRepository) {
		super (citiesRepository, airportsRepository);
	}

	@Override
	public Airports convert (AirportsUpdateRequest request) {

		Airports airports =  findById(request.getClass (), Long.valueOf (request.getId ()));

		if (request.getTitle ()!=null && !request.getTitle ().equals (airports.getTitle ())) {
			isUniqueAirports (request.getClass (), request.getTitle ());
		}

		if (request.getCities ()!=null) {
			airports.setCities (findCity (request.getClass (), request.getCities ()));
		}

		return doConvert (airports, request);
	}
}
