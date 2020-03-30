package com.airline.util.converters;

import com.airline.controller.request.FlightSaveRequest;
import com.airline.controller.request.FlightUpdateRequest;
import com.airline.entity.Flights;
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
