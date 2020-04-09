package com.airport.util.converters.airplanes;

import com.airport.controller.request.create.AirplanesSaveRequest;
import com.airport.entity.Airplanes;
import org.springframework.stereotype.Component;

@Component
public class ConverterSaveRequestAirplanes extends ConverterRequestAirplanes<AirplanesSaveRequest, Airplanes> {

	@Override
	public Airplanes convert (AirplanesSaveRequest request) {

        Airplanes airplanes = new Airplanes ();

        airplanes.setCountries(findCountries(request.getClass (),request.getCountry ()));

        isUniqueModelAirplanes (request.getClass (), request.getModel());

		return doConvert (airplanes, request);
	}
}
