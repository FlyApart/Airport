package com.airport.controller.converters.cities;

import com.airport.controller.request.create.CitiesSaveRequest;
import com.airport.entity.Cities;
import com.airport.repository.springdata.CitiesRepository;
import com.airport.repository.springdata.CountriesRepository;
import org.springframework.stereotype.Component;

@Component
public class ConverterSaveRequestCities extends ConverterRequestCities<CitiesSaveRequest, Cities> {

	public ConverterSaveRequestCities (CitiesRepository citiesRepository, CountriesRepository countriesRepository) {
		super (citiesRepository, countriesRepository);
	}

	@Override
	public Cities convert (CitiesSaveRequest request) {
		Cities cities = new Cities ();

		cities.setCountries (findCountries (request.getClass (), request.getCountry ()));

		uniqueCitiesName (request.getClass (), request.getName ());

		return doConvert (cities, request);
	}
}
