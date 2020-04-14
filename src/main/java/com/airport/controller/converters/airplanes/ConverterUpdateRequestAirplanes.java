package com.airport.controller.converters.airplanes;

import com.airport.controller.request.change.AirplanesUpdateRequest;
import com.airport.entity.Airplanes;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.AirplanesRepository;
import com.airport.repository.springdata.CountriesRepository;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class ConverterUpdateRequestAirplanes extends ConverterRequestAirplanes<AirplanesUpdateRequest, Airplanes> {

	public ConverterUpdateRequestAirplanes (CountriesRepository countriesRepository, AirplanesRepository airplanesRepository) {
		super (countriesRepository, airplanesRepository);
	}

	@Override
	public Airplanes convert (AirplanesUpdateRequest request) {

		Airplanes airplanes = ofNullable (entityManager.find (Airplanes.class, Long.valueOf (request.getId ()))).orElseThrow (() -> new ConversionException (AirplanesUpdateRequest.class, Airplanes.class, request, new EntityNotFoundException (Airplanes.class, request.getId ())));

		isUniqueModelAirplanes (request.getClass (), request.getModel ());

		airplanes.setCountries (findCountries (request.getClass (), request.getCountry ()));

		return doConvert (airplanes, request);
	}
}
