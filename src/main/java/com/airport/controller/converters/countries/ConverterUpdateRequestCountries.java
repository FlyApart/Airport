package com.airport.controller.converters.countries;

import com.airport.controller.request.change.CountriesUpdateRequest;
import com.airport.entity.Countries;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.CountriesRepository;
import org.springframework.stereotype.Component;

@Component
public class ConverterUpdateRequestCountries extends ConverterRequestCountries<CountriesUpdateRequest, Countries> {


	public ConverterUpdateRequestCountries (CountriesRepository countriesRepository) {
		super (countriesRepository);
	}

	@Override
	public Countries convert (CountriesUpdateRequest request) {
		Countries countries;

		if (request.getId () != null) {
			countries = findById (request.getClass (),Long.valueOf (request.getId ()));

			if (request.getName ()!=null && !request.getName ()
			                                       .equals (countries.getName ())){

				isUniqueCountries (request.getClass (),request.getName ());
			}
		}
		else if (request.getName () != null) {
			countries = findCountriesByName (request.getClass (), request.getName ());
		}
		else {
			throw new ConversionException (CountriesUpdateRequest.class, Countries.class, request, new EntityNotFoundException (request, Countries.class));
		}
		return doConvert (countries, request);
	}
}
