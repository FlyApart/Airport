package com.airport.controller.converters.airports;

import com.airport.controller.converters.EntityConverter;
import com.airport.controller.exceptions.ArgumentOfMethodNotValidException;
import com.airport.controller.exceptions.ConversionException;
import com.airport.controller.exceptions.EntityAlreadyExistException;
import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.change.AirportsUpdateRequest;
import com.airport.controller.request.create.AirportsSaveRequest;
import com.airport.entity.Airports;
import com.airport.entity.Cities;
import com.airport.entity.Countries;

import javax.persistence.NoResultException;

public abstract class ConverterRequestAirports<S, T> extends EntityConverter<S, T> {

	protected Airports doConvert (Airports airports, AirportsSaveRequest entity) {
		airports.setTitle(entity.getTitle());
		return airports;
	}

	protected Airports doConvert (Airports airports, AirportsUpdateRequest entity) {
		if (entity.getTitle () != null) airports.setTitle(entity.getTitle());
		return airports;
	}

    Cities findCity (Class<?> sClass, String city) {
        Cities cities;
		try {
            cities = entityManager.createQuery ("select c from Cities c where c.name=:name", Cities.class)
			                         .setParameter ("name", city)
			                         .getSingleResult ();
		} catch (NumberFormatException e) {
			throw new ConversionException (sClass, Airports.class, city, new ArgumentOfMethodNotValidException (Cities.class,city));
		} catch (NoResultException e) {
			throw new ConversionException (sClass, Airports.class, city,
                    new EntityNotFoundException (" Cities = " + city, Countries.class));
		}
		return cities;
	}


	void isUniqueAirports(Class<?> sClass, String title){
		try {
			entityManager.createQuery ("select c from Airports c where c.title =:title", Airports.class)
			             .setParameter ("title", title)
			             .getSingleResult ();

		} catch (NumberFormatException e) {
			throw new ConversionException(sClass, Airports.class, title,
					new ArgumentOfMethodNotValidException ("title = " + title));
		} catch (NoResultException e) {
		   return;
		}
		throw new ConversionException(sClass, Airports.class, title,
				new EntityAlreadyExistException (Airports.class,"title = "+title));

	}
}
