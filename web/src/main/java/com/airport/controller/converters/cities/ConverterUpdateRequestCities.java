package com.airport.controller.converters.cities;

import com.airport.controller.request.change.CitiesUpdateRequest;
import com.airport.entity.Cities;
import com.airport.repository.springdata.CitiesRepository;
import com.airport.repository.springdata.CountriesRepository;
import org.springframework.stereotype.Component;

@Component
public class ConverterUpdateRequestCities extends ConverterRequestCities<CitiesUpdateRequest, Cities> {



	public ConverterUpdateRequestCities (CitiesRepository citiesRepository, CountriesRepository countriesRepository) {
		super (citiesRepository, countriesRepository);
	}

	@Override
	public Cities convert (CitiesUpdateRequest request) {
		Cities cities = findById(request.getClass (),Long.valueOf (request.getId ()));

		if (request.getName ()!=null && !request.getName ()
		                                       .equals (cities.getName ())){
			uniqueCitiesName (request.getClass (), request.getName ());
		}

		if (request.getCountry ()!=null){
			cities.setCountries (findCountries (request.getClass (), request.getCountry ()));
		}

		return doConvert (cities, request);
	}
}
