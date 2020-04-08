package com.airport.util.converters.cities;

import com.airport.controller.exceptions.ConversionException;
import com.airport.controller.exceptions.EntityAlreadyExistException;
import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.exceptions.ArgumentOfMethodNotValidException;
import com.airport.controller.request.change.CitiesUpdateRequest;
import com.airport.controller.request.create.CitiesSaveRequest;
import com.airport.controller.request.create.PassengerSaveRequest;
import com.airport.entity.Cities;
import com.airport.entity.Countries;
import com.airport.entity.Passengers;
import com.airport.entity.Passports;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;

@Component
public class ConverterSaveRequestCities extends ConverterRequestCities <CitiesSaveRequest, Cities> {

	@Override
	public Cities convert (CitiesSaveRequest request) {
        Cities cities = new Cities ();

        if (request.getCountriesId()==null && request.getName()==null){
            throw new ConversionException (CitiesUpdateRequest.class, Cities.class, request,
                    new EntityNotFoundException (request, Cities.class));
        }

        cities.setCountries(findCountries(request.getCountriesId()));

		try {
			entityManager.createQuery ("select c from Cities c where c.name =:name ", Cities.class)
			                         .setParameter ("name", request.getName ())
			             .getSingleResult ();

		} catch (NumberFormatException e) {
			throw new ConversionException(CitiesSaveRequest.class, Cities.class, request,
					new ArgumentOfMethodNotValidException (request));
		} catch (NoResultException e) {
			return doConvert (cities, request);
		}
		throw new ConversionException(TypeDescriptor.valueOf (CitiesSaveRequest.class), TypeDescriptor.valueOf (Cities.class), request,
		new EntityAlreadyExistException (" name = " + request.getName ()));
	}
}
