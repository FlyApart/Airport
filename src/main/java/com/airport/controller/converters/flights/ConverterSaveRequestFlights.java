package com.airport.controller.converters.flights;

import com.airport.controller.request.create.FlightsSaveRequest;
import com.airport.entity.Flights;
import org.springframework.stereotype.Component;

@Component
public class ConverterSaveRequestFlights extends ConverterRequestFlights<FlightsSaveRequest, Flights> {

	@Override
	public Flights convert (FlightsSaveRequest request) {

		Flights flights = new Flights ();

		flights.setAirlines (findAirline (request.getClass (), request.getAirlinesName ()));
		flights.setAirplane (findAirplanes (request.getClass (), Long.valueOf (request.getAirplaneID ())));
		flights.setArriveAirport (findAirport (request.getClass (), request.getArriveAirportTitle ()));
		flights.setDepartureAirport (findAirport (request.getClass (), request.getDepartureAirportTitle ()));

		return doConvert (flights, request);
	}
}
