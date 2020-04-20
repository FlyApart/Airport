package com.airport.service;

import com.airport.entity.Flights;

import java.util.List;

public interface FlightService {

	Flights deleteFlightDiscounts (String id, List<Long> discountIds);

	List<Flights> findByParam (Flights flights);

	List<String> findAllOccupiedSeats (Long flightsId, Boolean isEmpty);

}
