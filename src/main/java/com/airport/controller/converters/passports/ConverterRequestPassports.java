package com.airport.controller.converters.passports;

import com.airport.controller.converters.EntityConverter;
import com.airport.controller.request.change.PassportUpdateRequest;
import com.airport.controller.request.create.PassportSaveRequest;
import com.airport.entity.Passengers;
import com.airport.entity.Passports;
import com.airport.entity.PassportsTypes;
import com.airport.exceptions.ArgumentOfMethodNotValidException;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityAlreadyExistException;
import com.airport.exceptions.EntityNotFoundException;

import javax.persistence.NoResultException;
import java.util.Optional;

public abstract class ConverterRequestPassports<S, T> extends EntityConverter<S, T> {

	protected Passports doConvert (Passports passports, PassportSaveRequest entity) {
		passports.setTypes (entity.getTypes ());
		passports.setSeries (Long.valueOf (entity.getSeries ()));
		passports.setNumber (Long.valueOf (entity.getNumber ()));
		return passports;
	}

	protected Passports doConvert (Passports passports, PassportUpdateRequest entity) {
		if (entity.getNumber () != null) passports.setNumber (Long.valueOf (entity.getNumber ()));
		if (entity.getTypes () != null) passports.setTypes (entity.getTypes ());
		if (entity.getSeries () != null) passports.setSeries (Long.valueOf (entity.getSeries ()));

		return passports;
	}

	protected Passports uniquePassengerIdAndTypes (Passengers passengers, PassportsTypes passportsTypes) {
		for (Passports passport : passengers.getPassports ()) {
			if (passport.getTypes () == passportsTypes) {
				return passport;
			}
		}
		return null;
	}

	protected void isUniquePassportsNumberAndSeries (Class<?> sClass, String number, String series) {

		try {
			entityManager.createQuery ("select p from Passports p where p.number =:number and p.series =:series", Passports.class)
			             .setParameter ("number", Long.valueOf (number))
			             .setParameter ("series", Long.valueOf (series))
			             .getSingleResult ();
		} catch (NumberFormatException e) {
			throw new ConversionException (sClass, Passports.class, number.concat (" " + series), new ArgumentOfMethodNotValidException ("Passport with number = " + number + ", series = " + series));
		} catch (NoResultException e) {
			return;
		}
		throw new ConversionException (sClass, Passports.class, number.concat (" " + series), new EntityAlreadyExistException ("Passport with number = " + number + ", series = " + series));
	}

	protected Passengers findByPassengerId (String passengerId) {
		return Optional.ofNullable (entityManager.find (Passengers.class, Long.valueOf (passengerId)))
		               .orElseThrow (() -> new ConversionException (PassportSaveRequest.class, Passports.class, passengerId, new EntityNotFoundException (Passengers.class, passengerId)));

	}

}
