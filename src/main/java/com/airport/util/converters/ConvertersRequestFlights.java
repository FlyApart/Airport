package com.airport.util.converters;

import com.airport.controller.request.change.FlightUpdateRequest;
import com.airport.controller.request.create.FlightSaveRequest;
import com.airport.entity.Flights;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConvertersRequestFlights implements Converter<FlightSaveRequest,Flights> {

	@Override
	public Flights convert (FlightSaveRequest entity) {
		Flights flights = new Flights ();
		flights.setDepartureDate (entity.getDepartureDate ());
		flights.setArriveDate (entity.getArriveDate ());
		flights.setPrice (entity.getPrice ());
		flights.setFightsNumber (entity.getFightsNumber ());
		return flights;
	}

	public Flights convertUpdate (FlightUpdateRequest entity, Flights flights) {
		flights.setDepartureDate (entity.getDepartureDate ());
		flights.setArriveDate (entity.getArriveDate ());
		flights.setPrice (entity.getPrice ());
		return flights;
	}
}
