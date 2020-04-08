package com.airport.util.converters.passports;

import com.airport.controller.exceptions.ConversionException;
import com.airport.controller.exceptions.EntityAlreadyExistException;
import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.change.PassportUpdateRequest;
import com.airport.entity.Passengers;
import com.airport.entity.Passports;
import org.springframework.stereotype.Component;

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

	@Override
	public Passports convert (PassportUpdateRequest request) {
		Passports passports = new Passports ();


		if (request.getId () != null) {
			passports = findById (passports, request);
            if (request.getPassengerId()!=null && !request.getPassengerId().equals(passports.getPassengersId().getId().toString())){
                throw new ConversionException (PassportUpdateRequest.class, Passports.class, request,
                        new EntityNotFoundException ("id ="+request.getId()+" and passengerId = "+request.getPassengerId(),
                                Passports.class));
            }
		}
		else if (request.getTypes()!=null && request.getPassengerId () != null) {
            Passengers passengers = findByPassengerId(request.getPassengerId());
                Passports uniquePassengerIdAndTypes = uniquePassengerIdAndTypes(passengers,request.getTypes());
                if(uniquePassengerIdAndTypes !=null){
                    passports = uniquePassengerIdAndTypes;
                }else {
                    passports.setPassengersId(passengers);
                }
        }
		else {
            throw new ConversionException (PassportUpdateRequest.class, Passports.class, request,
                    new EntityNotFoundException (request, Passports.class));
        }

        if(request.getNumber()!=null&& request.getSeries()!=null){
            isUniquePassportsNumberAndSeries(request.getClass(),request.getNumber(),request.getSeries());
		}

		return doConvert (passports, request);
	}
}
