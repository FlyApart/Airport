package com.airport.controller.converters.flights;

import com.airport.controller.request.create.FlightsSaveRequest;
import com.airport.entity.Flights;
import com.airport.repository.springdata.AirlinesRepository;
import com.airport.repository.springdata.AirplanesRepository;
import com.airport.repository.springdata.AirportsRepository;
import com.airport.repository.springdata.DiscountsRepository;
import com.airport.repository.springdata.FlightsRepository;
import org.springframework.stereotype.Component;

@Component
public class ConverterSaveRequestFlights extends ConverterRequestFlights<FlightsSaveRequest, Flights> {



	public ConverterSaveRequestFlights (FlightsRepository flightsRepository, AirportsRepository airportsRepository, AirplanesRepository airplanesRepository, AirlinesRepository airlinesRepository, DiscountsRepository discountsRepository) {
		super (flightsRepository, airportsRepository, airplanesRepository, airlinesRepository, discountsRepository);
	}

	@Override
	public Flights convert (FlightsSaveRequest request) {

		Flights flights = new Flights ();

		flights.setAirline (findAirline (request.getClass (), request.getAirlinesName ()));
		flights.setAirplane (findAirplanes (request.getClass (), Long.valueOf (request.getAirplaneID ())));
		flights.setArriveAirport (findAirport (request.getClass (), request.getArriveAirportTitle ()));
		flights.setDepartureAirport (findAirport (request.getClass (), request.getDepartureAirportTitle ()));
		if (!flights.getDiscount ().isEmpty ()) {
			flights.setDiscount (findDiscounts (request.getClass (), request.getDiscountId ()));
		}
		uniqueFlightNumber (request.getClass (), request.getFightsNumber ());

		return doConvert (flights, request);
	}
}
