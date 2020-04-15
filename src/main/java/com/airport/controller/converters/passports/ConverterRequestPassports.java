package com.airport.controller.converters.passports;

import com.airport.controller.converters.EntityConverter;
import com.airport.controller.request.change.PassportUpdateRequest;
import com.airport.controller.request.create.PassportSaveRequest;
import com.airport.entity.Passenger;
import com.airport.entity.Passports;
import com.airport.entity.PassportsTypes;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityAlreadyExistException;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.repository.springdata.PassportsRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ConverterRequestPassports<S, T> extends EntityConverter<S, T> {

	private final PassportsRepository passportsRepository;
	private final PassengersRepository passengersRepository;

	protected Passports doConvert (Passports passports, PassportSaveRequest entity) {

		passports.setTypes (entity.getTypes ());
		passports.setSeries (Long.valueOf (entity.getSeries ()));
		passports.setNumber (Long.valueOf (entity.getNumber ()));
		return passports;
	}

	protected Passports doConvert (Passports passports, PassportUpdateRequest entity) {
		if (entity.getNumber () != null) {
			passports.setNumber (Long.valueOf (entity.getNumber ()));
		}

		if (entity.getTypes () != null && !entity.getTypes ()
		                                         .equals (PassportsTypes.NOT_SELECTED)) {
			passports.setTypes (entity.getTypes ());
		}

		if (entity.getSeries () != null) {
			passports.setSeries (Long.valueOf (entity.getSeries ()));
		}

		return passports;
	}

	protected Passports uniquePassengerIdAndTypes (Passenger passenger, PassportsTypes passportsTypes) {
		for (Passports passport : passenger.getPassports ()) {
			if (passport.getTypes () == passportsTypes) {
				return passport;
			}
		}
		return null;
	}

	protected void isUniquePassportsNumberAndSeries (Class<?> sClass, Long number, Long series) {

		boolean unique = passportsRepository.findByNumberAndSeries (number, series)
		                                     .isPresent ();
		if (unique){
			throw new ConversionException (sClass, Passports.class, number +" "+ series,
					new EntityAlreadyExistException ("Passport with number = " + number + ", series = " + series));
		}
	}

	protected Passenger findByPassengerId (Class<?> sClass, Long passengerId) {
		return passengersRepository.findById (passengerId)
		               .orElseThrow (() -> new ConversionException (sClass, Passports.class, passengerId, new EntityNotFoundException (Passenger.class, passengerId)));
	}

	protected Passports findById (Class<?> sClass, Long id) {
        return passportsRepository.findById (id)
                                  .orElseThrow (() -> new ConversionException (sClass, Passports.class, id, new EntityNotFoundException (Passports.class, id)));

	}

}
