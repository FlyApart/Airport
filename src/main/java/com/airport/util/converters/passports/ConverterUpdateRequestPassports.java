package com.airport.util.converters.passports;

import com.airport.controller.exceptions.ConversionException;
import com.airport.controller.exceptions.EntityAlreadyExistException;
import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.exceptions.MethodArgumentNotValidException;
import com.airport.controller.request.change.PassportUpdateRequest;
import com.airport.controller.request.create.PassportSaveRequest;
import com.airport.entity.Passports;
import com.airport.entity.PassportsTypes;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;

import static java.util.Optional.ofNullable;

//import javax.persistence.NoResultException;

@Component
public class ConverterUpdateRequestPassports extends ConverterRequestPassports<PassportUpdateRequest, Passports> {

	Passports findById (Passports passports, PassportUpdateRequest request) {
		passports = ofNullable (entityManager.find (Passports.class, Long.valueOf (request.getId ())))
                            .orElseThrow (() -> new ConversionException (PassportUpdateRequest.class, Passports.class, request,
                                    new EntityNotFoundException (Passports.class, request.getId ())));
		return passports;
	}

	Passports findByTypesAndPassengerId (Passports passports, PassportUpdateRequest request) {
		try {
			passports = entityManager.createQuery ("select p from Passports p where p.types =:types and " + "p.passengersId.id =:passengersId", Passports.class)
			                         .setParameter ("types", request.getTypes ())
			                         .setParameter ("passengersId", Long.valueOf (request.getPassengerId ()))
			                         .getSingleResult ();
		} catch (NumberFormatException e) {
			throw new ConversionException (PassportUpdateRequest.class, Passports.class, request, new MethodArgumentNotValidException (request));
		} catch (NoResultException e) {
			throw new ConversionException (PassportUpdateRequest.class, Passports.class, request,
                    new EntityNotFoundException ("type = " + request.getTypes () + ", passenger id = " + request.getPassengerId (), Passports.class));
		}
		return passports;
	}

	void checkUniqueNumberAndSeries (PassportUpdateRequest request) {
		try {
			entityManager.createQuery ("select p from Passports p where p.number =:number and p.series =:series", Passports.class)
			             .setParameter ("number", Long.valueOf (request.getNumber ()))
			             .setParameter ("series", Long.valueOf (request.getSeries ()))
			             .getSingleResult ();
		} catch (NumberFormatException e) {
			throw new ConversionException (PassportSaveRequest.class, Passports.class, request, new MethodArgumentNotValidException (request));
		} catch (NoResultException e) {
		    return;
		}
		throw new ConversionException (TypeDescriptor.valueOf (PassportUpdateRequest.class), TypeDescriptor.valueOf (Passports.class), request,
                new EntityAlreadyExistException ("Passport with (number = " + request.getNumber () + ", series = " + request.getSeries () + ") " +
                                                         "or (passengers id = " + request.getPassengerId () + ",  types = " + request.getTypes () + ") "));
	}


	@Override
	public Passports convert (PassportUpdateRequest request) {
		Passports passports = new Passports ();

		if (request.getId () != null) {
			passports = findById (passports, request);
            checkUniqueNumberAndSeries (request);
		}
		else if (request.getTypes () != PassportsTypes.NOT_SELECTED && request.getPassengerId () != null) {
			passports = findByTypesAndPassengerId (passports, request);
            checkUniqueNumberAndSeries(request);
		}
		else {
            throw new ConversionException (PassportUpdateRequest.class, Passports.class, request,
                    new EntityNotFoundException (request, Passports.class));
        }
		return doConvert (passports, request);
	}
}
