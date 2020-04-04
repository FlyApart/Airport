package com.airline.util.converters;

import com.airline.controller.request.PassportSaveRequest;
import com.airline.entity.Passports;
import com.airline.util.converters.parent.ConverterRequestPassports;
import com.airline.util.exceptions.EntityAlreadyExist;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class ConverterSaveRequestPassports extends ConverterRequestPassports<PassportSaveRequest, Passports> {


	@Override
	public Passports convert (PassportSaveRequest request) {
       /* Passports passports = ofNullable (entityManager.createQuery ("select p from Passports p where p.number =:number and " + "p.series =:series", Passports.class)
                                                        .setParameter ("number", request.getNumber ())
                                                        .setParameter ("series", request.getSeries ())
                                                        .getSingleResult ())
                                                        .orElseThrow (() -> new EntityAlreadyExist (Passengers.class,
                                                                "number = "+request.getNumber ()+", series = "+request.getSeries ()));*/
        Passports passports = new Passports ();
        try {
             ofNullable (entityManager.createQuery ("select p from Passports p where p.number =:number and "
                                                            + "p.series =:series", Passports.class)
                                                           .setParameter ("number", request.getNumber ())
                                                           .setParameter ("series", request.getSeries ()));
        }
        catch (Exception e){
            passports = new Passports ();
            return doConvert (passports, request);
        }
            throw new EntityAlreadyExist (Passports.class,
                    "number = "+request.getNumber ()+", series = "+request.getSeries ());

	}
}
