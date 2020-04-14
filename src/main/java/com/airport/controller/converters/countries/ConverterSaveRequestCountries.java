package com.airport.controller.converters.countries;

import com.airport.controller.request.create.CountriesSaveRequest;
import com.airport.controller.request.create.PassportSaveRequest;
import com.airport.entity.Countries;
import com.airport.entity.Passports;
import com.airport.exceptions.ArgumentOfMethodNotValidException;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityAlreadyExistException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;

@Component
public class ConverterSaveRequestCountries extends ConverterRequestCountries<CountriesSaveRequest, Countries> {
	@Override
	public Countries convert (CountriesSaveRequest request) {
		Countries countries = new Countries ();

		try {
			entityManager.createQuery ("select c from Countries c where c.name =:name ", Countries.class)
			             .setParameter ("name", request.getName ())
			             .getSingleResult ();

		} catch (NumberFormatException e) {
			throw new ConversionException (PassportSaveRequest.class, Passports.class, request, new ArgumentOfMethodNotValidException (request));
		} catch (NoResultException e) {
			return doConvert (countries, request);
		}
		throw new ConversionException (TypeDescriptor.valueOf (CountriesSaveRequest.class), TypeDescriptor.valueOf (Countries.class), request, new EntityAlreadyExistException ("Country with name = " + request.getName ()));
	}
}
