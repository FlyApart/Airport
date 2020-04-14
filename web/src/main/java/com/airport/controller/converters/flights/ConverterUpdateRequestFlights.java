package com.airport.controller.converters.flights;

import com.airport.controller.request.change.FlightsUpdateRequest;
import com.airport.entity.Flights;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class ConverterUpdateRequestFlights extends ConverterRequestFlights<FlightsUpdateRequest, Flights> {

	@Override
	public Flights convert (FlightsUpdateRequest request) {

		Flights flights = ofNullable (entityManager.find (Flights.class, Long.valueOf (request.getId ()))).orElseThrow (() -> new ConversionException (FlightsUpdateRequest.class, Flights.class, request, new EntityNotFoundException (Flights.class, request.getId ())));

		if (request.getAirlinesName () != null) {
			flights.setAirlines (findAirline (request.getClass (), request.getAirlinesName ()));
		}

		if (request.getAirplaneID () != null) {
			flights.setAirplane (findAirplanes (request.getClass (), Long.valueOf (request.getAirplaneID ())));
		}

		if (request.getArriveAirportTitle () != null) {
			flights.setArriveAirport (findAirport (request.getClass (), request.getArriveAirportTitle ()));
		}

		if (request.getDepartureAirportTitle () != null) {
			flights.setDepartureAirport (findAirport (request.getClass (), request.getDepartureAirportTitle ()));
		}

		return doConvert (flights, request);
	}
}
