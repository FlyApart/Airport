package com.airport.controller.converters.countries;

import com.airport.controller.request.create.CountriesSaveRequest;
import com.airport.entity.Countries;
import com.airport.repository.springdata.CountriesRepository;
import org.springframework.stereotype.Component;

@Component
public class ConverterSaveRequestCountries extends ConverterRequestCountries<CountriesSaveRequest, Countries> {

	public ConverterSaveRequestCountries (CountriesRepository countriesRepository) {
		super (countriesRepository);
	}

	@Override
	public Countries convert (CountriesSaveRequest request) {
		Countries countries = new Countries ();
		isUniqueCountries(request.getClass (),request.getName ());
		return doConvert (countries,request);
	}
}
