package com.airport.controller.converters.passports;

import com.airport.controller.request.create.PassportSaveRequest;
import com.airport.entity.Passengers;
import com.airport.entity.Passports;
import com.airport.exceptions.ArgumentOfMethodNotValidException;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityAlreadyExistException;
import org.springframework.stereotype.Component;

@Component
public class ConverterSaveRequestPassports extends ConverterRequestPassports<PassportSaveRequest, Passports> {
	@Override
	public Passports convert (PassportSaveRequest request) {

		Passports passports = new Passports ();

		if (request.getTypes () == null /*|| request.getTypes()== PassportsTypes.NOT_SELECTED*/) {
			throw new ConversionException (PassportSaveRequest.class, Passports.class, request, new ArgumentOfMethodNotValidException (Passports.class, " type = " + request.getTypes ()));
		}

		if (request.getPassengerId () != null) {
			Passengers passengers = findByPassengerId (request.getPassengerId ());

			if (uniquePassengerIdAndTypes (passengers, request.getTypes ()) != null) {
				throw new ConversionException (PassportSaveRequest.class, Passports.class, request.getTypes (), new EntityAlreadyExistException ("Passport with passengerId = " + request.getPassengerId () + ", type = " + request.getTypes ()));
			}

			passports.setPassengersId (passengers);
		}


		if (request.getSeries () != null & request.getNumber () != null) {
			isUniquePassportsNumberAndSeries (request.getClass (), request.getNumber (), request.getSeries ());
		} else {
			throw new ConversionException (PassportSaveRequest.class, Passports.class, request, new ArgumentOfMethodNotValidException (request));
		}


		return doConvert (passports, request);
	}
}
