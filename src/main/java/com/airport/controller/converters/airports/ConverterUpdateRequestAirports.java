package com.airport.controller.converters.airports;

import com.airport.controller.request.change.AirportsUpdateRequest;
import com.airport.entity.Airports;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class ConverterUpdateRequestAirports extends ConverterRequestAirports<AirportsUpdateRequest, Airports> {

	@Override
	public Airports convert (AirportsUpdateRequest request) {

		Airports airports = ofNullable (entityManager.find (Airports.class, Long.valueOf (request.getId ()))).orElseThrow (() -> new ConversionException (AirportsUpdateRequest.class, Airports.class, request, new EntityNotFoundException (Airports.class, request.getId ())));

		isUniqueAirports (request.getClass (), request.getTitle ());

		airports.setCities (findCity (request.getClass (), request.getCities ()));

		return doConvert (airports, request);
	}
}
