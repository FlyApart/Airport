package com.airport.util.converters.airlines;

import com.airport.controller.exceptions.ConversionException;
import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.change.AirlinesUpdateRequest;
import com.airport.entity.Airlines;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class ConverterUpdateRequestAirlines extends ConverterRequestAirlines<AirlinesUpdateRequest, Airlines> {




	@Override
	public Airlines convert (AirlinesUpdateRequest request) {

	    Airlines airlines =
                ofNullable (entityManager.find (Airlines.class, Long.valueOf (request.getId ())))
                           .orElseThrow (() -> new ConversionException (AirlinesUpdateRequest.class, Airlines.class, request,
                                   new EntityNotFoundException (Airlines.class, request.getId ())));

		isUniqueAirlines (request.getClass (), request.getName (), request.getWebsite ());

		airlines.setCountries(findCountries(request.getClass(),request.getCountry()));

		return doConvert (airlines, request);
	}
}
