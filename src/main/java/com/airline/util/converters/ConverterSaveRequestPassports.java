package com.airline.util.converters;

import com.airline.controller.request.PassportSaveRequest;
import com.airline.entity.Passports;
import com.airline.util.converters.parent.ConverterRequestPassports;
import com.airline.controller.exceptions.EntityAlreadyExistException;
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
