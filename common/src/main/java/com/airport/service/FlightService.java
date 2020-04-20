package com.airport.service;

import com.airport.entity.Flights;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlightService {

	Flights deleteFlightDiscounts (String id, List<Long> discountIds);

	List<Flights> findByParam (Flights flights, Pageable pageable);

	List<String> findAllOccupiedSeats (Long flightsId, Boolean isEmpty);

}
