package com.airline.service.impl;

import com.airline.controller.request.*;
import com.airline.entity.Flights;
import com.airline.entity.Passengers;
import com.airline.entity.Passports;
import com.airline.repository.*;
import com.airline.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	FlightsDao flightsDao;
	@Autowired
	AirportDao airportDao;
	@Autowired
	AirplaneDao airplaneDao;
	@Autowired
	AirlineDao airlineDao;
	@Autowired
	DiscountDao discountDao;

	@Override
	public Flights save (FlightSaveRequest entity)  {

		Flights flights = new Flights();
		flights.setFightsNumber (entity.getFightsNumber ());
		flights.setDepartureDate (entity.getDepartureDate ());
		flights.setArriveDate (entity.getArriveDate ());
		flights.setPrice (entity.getPrice ());

		flights.setAirplane (airplaneDao.findByModel (entity.getModelAirplane ()));
		flights.setDepartureAirport (airportDao.findByTitle (entity.getDepartureAirport ()));
		flights.setArriveAirport (airportDao.findByTitle (entity.getArriveAirport ()));
		flights.setAirlines (airlineDao.findByName (entity.getAirlines ()));

		flights.setDiscount (discountDao.findByIds (entity.getDiscount ()));
		Flights f = flightsDao.save (flights);
		return f;
	}


	@Override
	public Flights update (FlightUpdateRequest entity, Long id) {

		Flights flights = flightsDao.findById (id);

		flights.setDepartureDate (entity.getDepartureDate ());
		flights.setArriveDate (entity.getArriveDate ());
		flights.setPrice (entity.getPrice ());

		flights.setAirplane (airplaneDao.findByModel (entity.getModelAirplane ()));
		flights.setDepartureAirport (airportDao.findByTitle (entity.getDepartureAirport ()));
		flights.setArriveAirport (airportDao.findByTitle (entity.getArriveAirport ()));
		flights.setAirlines (airlineDao.findByName (entity.getAirlines ()));

		flights.setDiscount (discountDao.findByIds (entity.getDiscount ()));
		return flightsDao.update(flights);
	}
}
