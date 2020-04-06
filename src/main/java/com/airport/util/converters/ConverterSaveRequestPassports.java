package com.airport.util.converters;

import com.airport.controller.exceptions.EntityAlreadyExistException;
import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.save.PassportSaveRequest;
import com.airport.entity.Passengers;
import com.airport.entity.Passports;
import com.airport.util.converters.parent.ConverterRequestPassports;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConverterSaveRequestPassports extends ConverterRequestPassports<PassportSaveRequest, Passports> {

	@Override
	public Passports convert (PassportSaveRequest request) {
		Passports passports = new Passports ();

		Optional.ofNullable (entityManager.find (Passengers.class, Long.valueOf (request.getPassengerId ())))
				                           .orElseThrow (() -> new EntityNotFoundException (Passengers.class, request.getPassengerId ()));

		entityManager.find (Passengers.class, Long.valueOf (request.getPassengerId ()));


		try {
			entityManager.createQuery ("select p from Passports p where (p.number =:number and p.series =:series) " +
					                           "or (p.passengersId.id =:passengersId and p.types =:types)", Passports.class)
			             .setParameter ("number", Long.valueOf (request.getNumber ()))
			             .setParameter ("series", Long.valueOf (request.getSeries ()))
			             .setParameter ("passengersId", Long.valueOf (request.getPassengerId ()))
			             .setParameter ("types", request.getTypes ());

		} catch (NumberFormatException e) {
			throw new NumberFormatException ();
		} catch (IllegalArgumentException e) {
			return doConvert (passports, request);
		}

		throw new EntityAlreadyExistException (Passports.class, "number = " + request.getNumber () + ", series = " + request.getSeries ()+
				                                                        " or passengers id = "+request.getPassengerId ()+" types = "+request.getTypes ());
	}
}
