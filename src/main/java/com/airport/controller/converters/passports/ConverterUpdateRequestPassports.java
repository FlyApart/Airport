package com.airport.controller.converters.passports;

import com.airport.controller.request.change.PassportUpdateRequest;
import com.airport.entity.Passenger;
import com.airport.entity.Passports;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.repository.springdata.PassportsRepository;
import org.springframework.stereotype.Component;


@Component
public class ConverterUpdateRequestPassports extends ConverterRequestPassports<PassportUpdateRequest, Passports> {

	public ConverterUpdateRequestPassports (PassportsRepository passportsRepository, PassengersRepository passengersRepository) {
		super (passportsRepository, passengersRepository);
	}

	@Override
	public Passports convert (PassportUpdateRequest request) {

		Passports passports = new Passports ();


		if (request.getId () != null) {
			passports = findById (request.getClass (), Long.valueOf (request.getId ()));

			if (request.getPassengerId () != null && !request.getPassengerId ()
			                                                 .equals (passports.getPassengerId ()
			                                                                   .getId ()
			                                                                   .toString ())) {
				throw new ConversionException (PassportUpdateRequest.class, Passports.class, request,
						new EntityNotFoundException ("id =" + request.getId () + " and passengerId = " + request.getPassengerId (), Passports.class));
			}

		} else if (request.getTypes () != null && request.getPassengerId () != null) {

			Passenger passenger = findByPassengerId (request.getClass (), Long.valueOf (request.getPassengerId ()));

			Passports uniquePassengerIdAndTypes = uniquePassengerIdAndTypes (passenger, request.getTypes ());

			if (uniquePassengerIdAndTypes != null) {
				passports = uniquePassengerIdAndTypes;
			} else {
				passports.setPassengerId (passenger);
			}

		} else {
			throw new ConversionException (PassportUpdateRequest.class, Passports.class, request, new EntityNotFoundException (request, Passports.class));
		}


		if (request.getNumber () != null && request.getSeries () != null) {
			isUniquePassportsNumberAndSeries (request.getClass (), Long.valueOf (request.getNumber ()), Long.valueOf (request.getSeries ()));
		}
		else
		if (request.getNumber () != null){
			isUniquePassportsNumberAndSeries (request.getClass (), Long.valueOf (request.getNumber ()),passports.getSeries ());
		}
		else
		if (request.getSeries () != null){
			isUniquePassportsNumberAndSeries (request.getClass (), passports.getNumber (), Long.valueOf (request.getSeries ()));
		}

		return doConvert (passports, request);
	}
}
