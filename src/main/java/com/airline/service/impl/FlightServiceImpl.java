package com.airline.service.impl;

import com.airline.controller.request.FlightSaveRequest;
import com.airline.controller.request.FlightUpdateRequest;
import com.airline.entity.Flights;
import com.airline.repository.*;
import com.airline.service.FlightService;
import com.airline.util.converters.ConvertersRequestFlights;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightServiceImpl implements FlightService {


	private final FlightsDao flightsDao;

	private final AirportDao airportDao;

	private final AirplaneDao airplaneDao;

	private final AirlineDao airlineDao;

	private final DiscountDao discountDao;

	private final ConvertersRequestFlights convertersRequestFlights;

	@Override
	public Flights save (FlightSaveRequest entity) {

		Flights flights = convertersRequestFlights.convert (entity);

		flights.setAirplane (airplaneDao.findByModel (entity.getModelAirplane ()));
		flights.setDepartureAirport (airportDao.findByTitle (entity.getDepartureAirport ()));
		flights.setArriveAirport (airportDao.findByTitle (entity.getArriveAirport ()));
		flights.setAirlines (airlineDao.findByName (entity.getAirlines ()));
		flights.setDiscount (discountDao.findByIds (entity.getDiscount ()));
		return flightsDao.save (flights);
	}


	@Override
	public Flights update (FlightUpdateRequest entity, Long id) {

		Flights flights = convertersRequestFlights.convertUpdate (entity, flightsDao.findById (id));

		flights.setAirplane (airplaneDao.findByModel (entity.getModelAirplane ()));
		flights.setDepartureAirport (airportDao.findByTitle (entity.getDepartureAirport ()));
		flights.setArriveAirport (airportDao.findByTitle (entity.getArriveAirport ()));
		flights.setAirlines (airlineDao.findByName (entity.getAirlines ()));

		flights.setDiscount (discountDao.findByIds (entity.getDiscount ()));
		return flightsDao.update (flights);
	}
}
