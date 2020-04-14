package com.airport.controller.converters.airplanes;

import com.airport.controller.request.create.AirplanesSaveRequest;
import com.airport.entity.Airplanes;
import com.airport.repository.springdata.AirplanesRepository;
import com.airport.repository.springdata.CountriesRepository;
import org.springframework.stereotype.Component;

@Component
public class ConverterSaveRequestAirplanes extends ConverterRequestAirplanes<AirplanesSaveRequest, Airplanes> {


	public ConverterSaveRequestAirplanes (CountriesRepository countriesRepository, AirplanesRepository airplanesRepository) {
		super (countriesRepository, airplanesRepository);
	}

	@Override
	public Airplanes convert (AirplanesSaveRequest request) {

		Airplanes airplanes = new Airplanes ();

		airplanes.setCountries (findCountries (request.getClass (), request.getCountry ()));

		isUniqueModelAirplanes (request.getClass (), request.getModel ());

		return doConvert (airplanes, request);
	}
}
