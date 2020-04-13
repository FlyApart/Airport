package com.airport.controller.converters.airlines;

import com.airport.controller.converters.EntityConverter;
import com.airport.controller.exceptions.ArgumentOfMethodNotValidException;
import com.airport.controller.exceptions.ConversionException;
import com.airport.controller.exceptions.EntityAlreadyExistException;
import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.change.AirlinesUpdateRequest;
import com.airport.controller.request.create.AirlinesSaveRequest;
import com.airport.entity.Airlines;
import com.airport.entity.Countries;

import javax.persistence.NoResultException;

public abstract class ConverterRequestAirlines<S, T> extends EntityConverter<S, T> {

	protected Airlines doConvert (Airlines airlines, AirlinesSaveRequest entity) {
		airlines.setName (entity.getName ());
		airlines.setFleet (Long.valueOf (entity.getSumFleet ()));
		airlines.setFlightsCounts (Long.valueOf (entity.getFlightsCounts ()));
		airlines.setWebsite (entity.getWebsite ());
		return airlines;
	}

	protected Airlines doConvert (Airlines airlines, AirlinesUpdateRequest entity) {
		if (entity.getName () != null) airlines.setName (entity.getName ());
		if (entity.getSumFleet () != null) airlines.setFleet (Long.valueOf (entity.getSumFleet ()));
		if (entity.getFlightsCounts () != null) airlines.setFlightsCounts (Long.valueOf (entity.getFlightsCounts ()));
		if (entity.getWebsite () != null) airlines.setWebsite (entity.getWebsite ());
		return airlines;
	}

	Countries findCountries (Class<?> sClass, String country) {
		Countries countries;
		try {
			countries = entityManager.createQuery ("select c from Countries c where c.name=:name", Countries.class)
			                         .setParameter ("name", country)
			                         .getSingleResult ();
		} catch (NumberFormatException e) {
			throw new ConversionException (sClass, Airlines.class, country, new ArgumentOfMethodNotValidException (Countries.class,country));
		} catch (NoResultException e) {
			throw new ConversionException (sClass, Airlines.class, country, new EntityNotFoundException (" name = " + country, Countries.class));
		}
		return countries;
	}


	void isUniqueAirlines(Class<?> sClass, String name, String website){
		try {
			entityManager.createQuery ("select c from Airlines c where c.name =:name or c.website=:website", Airlines.class)
			             .setParameter ("name", name)
			             .setParameter ("website", website)
			             .getSingleResult ();

		} catch (NumberFormatException e) {
			throw new ConversionException(sClass, Airlines.class, name.concat(" "+website),
					new ArgumentOfMethodNotValidException ("name = " + name+" or website = "+website));
		} catch (NoResultException e) {
		   return;
		}
		throw new ConversionException(sClass, Airlines.class, name.concat(" "+website),
				new EntityAlreadyExistException (Airlines.class,"name = " + name+" or website = "+website));

	}
}
