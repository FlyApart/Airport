package com.airport.controller.converters.passports;

import com.airport.controller.request.create.PassportSaveRequest;
import com.airport.entity.Passenger;
import com.airport.entity.Passports;
import com.airport.entity.PassportsTypes;
import com.airport.exceptions.ArgumentOfMethodNotValidException;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityAlreadyExistException;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.repository.springdata.PassportsRepository;
import org.springframework.stereotype.Component;

@Component
public class ConverterSaveRequestPassports extends ConverterRequestPassports<PassportSaveRequest, Passports> {

	public ConverterSaveRequestPassports (PassportsRepository passportsRepository, PassengersRepository passengersRepository) {
		super (passportsRepository, passengersRepository);
	}

	@Override
	public Passports convert (PassportSaveRequest request) {

		Passports passports = new Passports ();

		if (request.getTypes () == null || request.getTypes()== PassportsTypes.NOT_SELECTED) {

			throw new ConversionException (PassportSaveRequest.class, Passports.class, request,
					new ArgumentOfMethodNotValidException (Passports.class, " type = " + request.getTypes ()));
		}

		if (request.getPassengerId () != null) {
			Passenger passenger = findByPassengerId (request.getClass (), Long.valueOf (request.getPassengerId ()));

			if (uniquePassengerIdAndTypes (passenger, request.getTypes ()) != null) {
				throw new ConversionException (PassportSaveRequest.class, Passports.class, request.getTypes (),
						new EntityAlreadyExistException ("Passport with passengerId = " + request.getPassengerId () + ", type = " + request.getTypes ()));
			}

			passports.setPassengerId (passenger);
		}


		if (request.getSeries () != null & request.getNumber () != null) {
			isUniquePassportsNumberAndSeries (request.getClass (), Long.valueOf (request.getNumber ()), Long.valueOf (request.getSeries ()));
		} else {
			throw new ConversionException (PassportSaveRequest.class, Passports.class, request, new ArgumentOfMethodNotValidException (request));
		}


		return doConvert (passports, request);
	}
}
