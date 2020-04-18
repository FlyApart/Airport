package com.airport.service.impl;

import com.airport.entity.Discounts;
import com.airport.entity.Flights;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.DiscountsRepository;
import com.airport.repository.springdata.FlightsRepository;
import com.airport.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

private final FlightsRepository flightsRepository;

private final DiscountsRepository discountsRepository;

	@Override
	public Flights deleteFlightDiscounts (String id, List<Long> discountIds) {

		Flights flights = flightsRepository.findById (Long.valueOf (id))
		                                   .orElseThrow (()-> new EntityNotFoundException (Flights.class, id));


// TODO fix this!!!
		List<Discounts> discountsList = discountsRepository.findByIds(new ArrayList<> (discountIds))
		                                                   .orElseThrow (()-> new EntityNotFoundException (Discounts.class, discountIds));
		flights.getDiscount ().removeAll (discountsList);

		return flightsRepository.saveAndFlush (flights);
	}
}


