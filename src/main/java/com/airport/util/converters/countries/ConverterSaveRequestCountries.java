package com.airport.util.converters.countries;

import com.airport.controller.exceptions.ConversionException;
import com.airport.controller.exceptions.EntityAlreadyExistException;
import com.airport.controller.exceptions.MethodArgumentNotValidException;
import com.airport.controller.request.create.CountriesSaveRequest;
import com.airport.controller.request.create.PassportSaveRequest;
import com.airport.entity.Countries;
import com.airport.entity.Passports;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;

@Component
public class ConverterSaveRequestCountries extends ConverterRequestCountries <CountriesSaveRequest, Countries> {
	@Override
	public Countries convert (CountriesSaveRequest request) {
        Countries countries = new Countries ();

		try {
			entityManager.createQuery ("select c from Countries c where c.name =:name ", Countries.class)
			                         .setParameter ("name", request.getName ())
			             .getSingleResult ();

		} catch (NumberFormatException e) {
			throw new ConversionException(PassportSaveRequest.class, Passports.class, request,
					new MethodArgumentNotValidException (request));
		} catch (NoResultException e) {
			return doConvert (countries, request);
		}
		throw new ConversionException(TypeDescriptor.valueOf (CountriesSaveRequest.class), TypeDescriptor.valueOf (Countries.class), request,
		new EntityAlreadyExistException ("Country with name = " + request.getName ()));
	}
}
