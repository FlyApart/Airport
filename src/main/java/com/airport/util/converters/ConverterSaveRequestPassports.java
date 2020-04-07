package com.airport.util.converters;

import com.airport.controller.exceptions.ConversionException;
import com.airport.controller.exceptions.EntityAlreadyExistException;
import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.exceptions.MethodArgumentNotValidException;
import com.airport.controller.request.create.PassportSaveRequest;
import com.airport.entity.Passengers;
import com.airport.entity.Passports;
import com.airport.util.converters.parent.ConverterRequestPassports;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.util.Optional;

@Component
public class ConverterSaveRequestPassports extends ConverterRequestPassports<PassportSaveRequest, Passports> {
	@Override
	public Passports convert (PassportSaveRequest request) {
		Passports passports = new Passports ();

		Passengers passengers = Optional.ofNullable (entityManager.find (Passengers.class, Long.valueOf (request.getPassengerId ())))
		        .orElseThrow (() -> new ConversionException(PassportSaveRequest.class, Passports.class, request,
				        new EntityNotFoundException (Passengers.class, request.getPassengerId ())));

		passports.setPassengersId (passengers);

		try {
			entityManager.createQuery ("select p from Passports p where (p.number =:number and p.series =:series) "+
			                          "or (p.passengersId.id =:passengersId and p.types =:types)", Passports.class)
			                         .setParameter ("number", Long.valueOf (request.getNumber ()))
			                         .setParameter ("series", Long.valueOf (request.getSeries ()))
			                         .setParameter ("passengersId", Long.valueOf (request.getPassengerId ()))
			                         .setParameter ("types", request.getTypes ())
			             .getSingleResult ();

		} catch (NumberFormatException e) {
			throw new ConversionException(PassportSaveRequest.class, Passports.class, request,
					new MethodArgumentNotValidException (request));
		} catch (NoResultException e) {
			return doConvert (passports, request);
		}
		throw new ConversionException(TypeDescriptor.valueOf (PassportSaveRequest.class), TypeDescriptor.valueOf (Passports.class), request,
		new EntityAlreadyExistException ("Passport with (number = " + request.getNumber () + ", series = " + request.getSeries () +
				                                       ") or (passengers id = " + request.getPassengerId () + ",  types = " + request.getTypes () + ") "));
	}
}
