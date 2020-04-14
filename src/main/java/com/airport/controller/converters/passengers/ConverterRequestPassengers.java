package com.airport.controller.converters.passengers;

import com.airport.controller.converters.EntityConverter;
import com.airport.controller.request.change.PassengerUpdateRequest;
import com.airport.controller.request.create.PassengerSaveRequest;
import com.airport.entity.Cities;
import com.airport.entity.Passengers;
import com.airport.entity.Passports;
import com.airport.entity.Status;
import com.airport.exceptions.ArgumentOfMethodNotValidException;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityAlreadyExistException;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.util.ProjectDate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.NoResultException;

@RequiredArgsConstructor
public abstract class ConverterRequestPassengers<S, T> extends EntityConverter<S, T> {

	private final BCryptPasswordEncoder passwordEncoder;

	protected Passengers doConvert (Passengers passengers, PassengerSaveRequest entity) {
		passengers.setName (entity.getName ());
		passengers.setSecondName (entity.getSecondName ());
		passengers.setPassword (passwordEncoder.encode (entity.getPassword ()));
		passengers.setBirthDate (entity.getBirthDate ());
		passengers.setCreated (new ProjectDate ().getCurrentTime ());
		passengers.setLogin (entity.getLogin ());
		passengers.setStatus (Status.ACTIVE);
		return passengers;
	}

	protected Passengers doConvert (Passengers passengers, PassengerUpdateRequest entity) {
		if (entity.getId () != null) passengers.setId (Long.valueOf (entity.getId ()));
		if (entity.getName () != null) passengers.setName (entity.getName ());
		if (entity.getSecondName () != null) passengers.setSecondName (entity.getSecondName ());
		if (entity.getPassword () != null) passengers.setPassword (passwordEncoder.encode (entity.getPassword ()));
		if (entity.getBirthDate () != null) passengers.setBirthDate (entity.getBirthDate ());
		passengers.setChanged (new ProjectDate ().getCurrentTime ());
		//passengers.setLogin (entity.getLogin ());
		return passengers;
	}

	protected Cities findCity (Class<?> sClass, String city) {
		Cities cities;
		try {
			cities = entityManager.createQuery ("select c from Cities c where c.name=:name", Cities.class)
			                      .setParameter ("name", city)
			                      .getSingleResult ();
		} catch (NumberFormatException e) {
			throw new ConversionException (sClass, Passengers.class, sClass, new ArgumentOfMethodNotValidException (city));
		} catch (NoResultException e) {
			throw new ConversionException (sClass, Passengers.class, sClass, new EntityNotFoundException ("City with name = " + city, Cities.class));
		}
		return cities;
	}

	protected void isUniqueLogin (Class<?> sClass, String login) {
		try {
			entityManager.createQuery ("select p from Passengers p where p.login =:login", Passengers.class)
			             .setParameter ("login", login)
			             .getSingleResult ();
		} catch (NumberFormatException e) {
			throw new ConversionException (sClass, Passports.class, login, new ArgumentOfMethodNotValidException ("Passengers with login = " + login));
		} catch (NoResultException e) {
			return;
		}
		throw new ConversionException (sClass, Passports.class, login, new EntityAlreadyExistException ("Passengers with login = " + login));
	}

}
