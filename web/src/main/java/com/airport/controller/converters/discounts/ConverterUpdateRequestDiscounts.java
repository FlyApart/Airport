package com.airport.controller.converters.discounts;

import com.airport.controller.request.change.DiscountsUpdateRequest;
import com.airport.entity.Discounts;
import com.airport.repository.springdata.DiscountsRepository;
import com.airport.repository.springdata.FlightsRepository;
import org.springframework.stereotype.Component;

@Component
public class ConverterUpdateRequestDiscounts extends ConverterRequestDiscounts<DiscountsUpdateRequest, Discounts> {


	public ConverterUpdateRequestDiscounts (DiscountsRepository discountsRepository, FlightsRepository flightsRepository) {
		super (discountsRepository, flightsRepository);
	}

	@Override
	public Discounts convert (DiscountsUpdateRequest request) {

		Discounts discounts = findDiscountsById(request.getClass (), Long.valueOf (request.getId ()));

		if (request.getFlightsId () != null) {
			discounts.setFlights (findFlights (request.getClass (), request.getFlightsId ()));
		}

		if (request.getTitle () != null && !request.getTitle ()
		                                           .equals (discounts.getTitle ())) {
			uniqueDiscountsName (request.getClass (), request.getTitle ());
		}

		return doConvert (discounts, request);
	}
}
