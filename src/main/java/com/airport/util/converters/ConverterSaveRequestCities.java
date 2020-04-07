package com.airport.util.converters;

import com.airport.controller.exceptions.ConversionException;
import com.airport.controller.exceptions.EntityAlreadyExistException;
import com.airport.controller.exceptions.MethodArgumentNotValidException;
import com.airport.controller.request.create.CitiesSaveRequest;
import com.airport.controller.request.create.PassportSaveRequest;
import com.airport.entity.Cities;
import com.airport.entity.Passports;
import com.airport.util.converters.parent.ConverterRequestCities;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;

@Component
public class ConverterSaveRequestCities extends ConverterRequestCities <CitiesSaveRequest, Cities> {

	@Override
	public Cities convert (CitiesSaveRequest request) {
        Cities cities = new Cities ();

		try {
			entityManager.createQuery ("select c from Cities c where c.name =:name ", Cities.class)
			                         .setParameter ("name", request.getName ())
			             .getSingleResult ();

		} catch (NumberFormatException e) {
			throw new ConversionException(CitiesSaveRequest.class, Cities.class, request,
					new MethodArgumentNotValidException (request));
		} catch (NoResultException e) {
			return doConvert (cities, request);
		}
		throw new ConversionException(TypeDescriptor.valueOf (CitiesSaveRequest.class), TypeDescriptor.valueOf (Cities.class), request,
		new EntityAlreadyExistException ("Cities with name = " + request.getName ()));
	}
}
