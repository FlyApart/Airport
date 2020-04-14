package com.airport.controller.converters.discounts;

import com.airport.controller.request.create.DiscountsSaveRequest;
import com.airport.entity.Discounts;
import org.springframework.stereotype.Component;

@Component
public class ConverterSaveRequestDiscounts extends ConverterRequestDiscounts<DiscountsSaveRequest, Discounts> {

	@Override
	public Discounts convert (DiscountsSaveRequest request) {
		Discounts discounts = new Discounts ();

		discounts.setFlights (findFlights (request.getClass (), request.getFlightsId ()));

		uniqueDiscountsName (request.getClass (), request.getTitle ());

		return doConvert (discounts, request);
	}
}
