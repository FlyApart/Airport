package com.airport.controller.converters.flights;

import com.airport.controller.request.select.FlightsQueryParams;
import com.airport.entity.Flights;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.CustomException;
import com.airport.repository.springdata.AirlinesRepository;
import com.airport.repository.springdata.AirplanesRepository;
import com.airport.repository.springdata.AirportsRepository;
import com.airport.repository.springdata.DiscountsRepository;
import com.airport.repository.springdata.FlightsRepository;
import org.springframework.stereotype.Component;

@Component
public class ConverterFindRequestFlights extends ConverterRequestFlights<FlightsQueryParams, Flights> {



	public ConverterFindRequestFlights (FlightsRepository flightsRepository, AirportsRepository airportsRepository, AirplanesRepository airplanesRepository, AirlinesRepository airlinesRepository, DiscountsRepository discountsRepository) {
		super (flightsRepository, airportsRepository, airplanesRepository, airlinesRepository, discountsRepository);
	}

	@Override
	public Flights convert (FlightsQueryParams request) {

		Flights flights = new Flights ();

		if (request.getArriveCity ().equals (request.getDepartureCity ())){
			throw new ConversionException (FlightsQueryParams.class, Flights.class,request,new CustomException ("Indicate different cities"));
		}
		return doConvert (flights, request);
	}
}
