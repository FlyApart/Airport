package com.airport.util.converters.discounts;

import com.airport.controller.exceptions.ConversionException;
import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.change.DiscountsUpdateRequest;
import com.airport.entity.Discounts;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class ConverterUpdateRequestDiscounts extends ConverterRequestDiscounts<DiscountsUpdateRequest, Discounts> {

	@Override
	public Discounts convert (DiscountsUpdateRequest request) {
		Discounts discounts = ofNullable (entityManager.find (Discounts.class, Long.valueOf (request.getId ())))
                                      .orElseThrow (() -> new ConversionException (DiscountsUpdateRequest.class, Discounts.class, request,
                                              new EntityNotFoundException (Discounts.class, request.getId ())));
		if(request.getFlightsId ()!=null) discounts.setFlights (findFlights (request.getClass (), request.getFlightsId ()));

		uniqueDiscountsName (request.getClass (), request.getTitle ());


		return doConvert (discounts, request);
	}
}
