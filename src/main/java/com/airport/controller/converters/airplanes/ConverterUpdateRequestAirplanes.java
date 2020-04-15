package com.airport.controller.converters.airplanes;

import com.airport.controller.request.change.AirplanesUpdateRequest;
import com.airport.entity.Airplanes;
import com.airport.repository.springdata.AirplanesRepository;
import com.airport.repository.springdata.CountriesRepository;
import org.springframework.stereotype.Component;

@Component
public class ConverterUpdateRequestAirplanes extends ConverterRequestAirplanes<AirplanesUpdateRequest, Airplanes> {

	public ConverterUpdateRequestAirplanes (CountriesRepository countriesRepository, AirplanesRepository airplanesRepository) {
		super (countriesRepository, airplanesRepository);
	}

	@Override
	public Airplanes convert (AirplanesUpdateRequest request) {

		Airplanes airplanes = findById(request.getClass (), Long.valueOf (request.getId ()));

		if (request.getModel ()!=null && !request.getModel ()
		                                         .equals (airplanes.getModel ())){
			isUniqueModelAirplanes (request.getClass (), request.getModel ());
		}

		if (request.getCountry ()!=null) {
			airplanes.setCountries (findCountries (request.getClass (), request.getCountry ()));
		}
		return doConvert (airplanes, request);
	}
}
