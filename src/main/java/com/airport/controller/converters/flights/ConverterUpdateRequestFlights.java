package com.airport.controller.converters.flights;

import com.airport.controller.request.change.FlightsUpdateRequest;
import com.airport.entity.Discounts;
import com.airport.entity.Flights;
import com.airport.repository.springdata.AirlinesRepository;
import com.airport.repository.springdata.AirplanesRepository;
import com.airport.repository.springdata.AirportsRepository;
import com.airport.repository.springdata.DiscountsRepository;
import com.airport.repository.springdata.FlightsRepository;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ConverterUpdateRequestFlights extends ConverterRequestFlights<FlightsUpdateRequest, Flights> {

	public ConverterUpdateRequestFlights (FlightsRepository flightsRepository, AirportsRepository airportsRepository, AirplanesRepository airplanesRepository, AirlinesRepository airlinesRepository, DiscountsRepository discountsRepository) {
		super (flightsRepository, airportsRepository, airplanesRepository, airlinesRepository, discountsRepository);
	}

	@Override
	public Flights convert (FlightsUpdateRequest request) {

		Flights flights = findFlights (request.getClass (), request.getId());

		if (request.getAirlinesName () != null) {
			flights.setAirline (findAirline (request.getClass (), request.getAirlinesName ()));
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

		if (request.getFightsNumber () != null && !request.getFightsNumber ()
		                                                 .equals (flights.getFightsNumber ())) {
			uniqueFlightNumber (request.getClass (), flights.getFightsNumber ());
		}

		if (request.getDiscountId () != null ) {
			Set<Long> discountId = request.getDiscountId ();
			for (Discounts discounts : flights.getDiscount ()) {
					discountId.add (discounts.getId ());
			}
			flights.setDiscount (findDiscounts (request.getClass (), discountId));
		}

		return doConvert (flights, request);
	}
}
