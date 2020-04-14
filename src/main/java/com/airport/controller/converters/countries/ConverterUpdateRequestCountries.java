package com.airport.controller.converters.countries;

import com.airport.controller.request.change.CountriesUpdateRequest;
import com.airport.entity.Countries;
import com.airport.exceptions.ArgumentOfMethodNotValidException;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;

import static java.util.Optional.ofNullable;

@Component
public class ConverterUpdateRequestCountries extends ConverterRequestCountries<CountriesUpdateRequest, Countries> {


	Countries findById (Countries countries, CountriesUpdateRequest request) {
		countries = ofNullable (entityManager.find (Countries.class, Long.valueOf (request.getId ()))).orElseThrow (() -> new ConversionException (CountriesUpdateRequest.class, Countries.class, request, new EntityNotFoundException (Countries.class, request.getId ())));
		return countries;
	}

	Countries findCountriesByName (Countries countries, CountriesUpdateRequest request) {
		try {
			countries = entityManager.createQuery ("select c from Countries c where c.name =:name ", Countries.class)
			                         .setParameter ("name", request.getName ())
			                         .getSingleResult ();
		} catch (NumberFormatException e) {
			throw new ConversionException (CountriesUpdateRequest.class, Countries.class, request, new ArgumentOfMethodNotValidException (request));
		} catch (NoResultException e) {
			throw new ConversionException (CountriesUpdateRequest.class, Countries.class, request, new EntityNotFoundException (" Country with name = " + request.getName (), Countries.class));
		}
		return countries;
	}


	@Override
	public Countries convert (CountriesUpdateRequest request) {
		Countries countries = new Countries ();

		if (request.getId () != null) {
			countries = findById (countries, request);
		} else if (request.getName () != null) {
			countries = findCountriesByName (countries, request);
		} else {
			throw new ConversionException (CountriesUpdateRequest.class, Countries.class, request, new EntityNotFoundException (request, Countries.class));
		}
		return doConvert (countries, request);
	}
}
