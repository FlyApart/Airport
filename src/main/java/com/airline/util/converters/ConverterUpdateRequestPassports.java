package com.airline.util.converters;

import com.airline.controller.request.PassportUpdateRequest;
import com.airline.entity.Passports;
import com.airline.util.converters.parent.ConverterRequestPassports;
import com.airline.util.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class ConverterUpdateRequestPassports extends ConverterRequestPassports<PassportUpdateRequest, Passports> {


	@Override
	public Passports convert (PassportUpdateRequest request) {
	if (request.getId ()!=null){// TODO add security
		Passports passports =
				ofNullable(entityManager.find(Passports.class, request.getId ()))
						.orElseThrow(() -> new EntityNotFoundException (Passports.class, request.getId ()));
		return doConvert(passports, request);
	}

	else {
		Passports passports = ofNullable (entityManager.createQuery ("select p from Passports p where p.title =:title and " + "p.passengersId.id =:passengersId", Passports.class)
		                                     .setParameter ("title", request.getTitle ())
		                                     .setParameter ("passengersId", Long.valueOf(request.getPassengerId ()))
		                                     .getSingleResult ()).orElseThrow (() -> new EntityNotFoundException (Passports.class, request.getId ()));
		return doConvert( passports, request);
	}


	}
}