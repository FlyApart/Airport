package com.airport.service;

import com.airport.entity.Flights;

import java.util.List;

public interface FlightService {

	Flights deleteFlightDiscounts (String id, List<Long> discountIds);

}
