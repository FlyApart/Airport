package com.airport.util.converters;

import com.airport.controller.exceptions.EntityAlreadyExistException;
import com.airport.controller.request.save.PassportSaveRequest;
import com.airport.entity.Passports;
import com.airport.util.converters.parent.ConverterRequestPassports;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class ConverterSaveRequestPassports extends ConverterRequestPassports<PassportSaveRequest, Passports> {


	@Override
	public Passports convert (PassportSaveRequest request) {
        Passports passports = new Passports ();
        try {
             ofNullable (entityManager.createQuery ("select p from Passports p where p.number =:number and "
                                                            + "p.series =:series", Passports.class)
                                                           .setParameter ("number", Integer.valueOf(request.getNumber ()))
                                                           .setParameter ("series", Integer.valueOf(request.getSeries ())));
        }
        catch (NumberFormatException e){
            throw new NumberFormatException(
                    "number = "+request.getNumber ()+", series = "+request.getSeries ());
        }

        catch (IllegalArgumentException e){
            return doConvert (passports, request);
        }

        throw new EntityAlreadyExistException(Passports.class,
                    "number = "+request.getNumber ()+", series = "+request.getSeries ());
	}
}
