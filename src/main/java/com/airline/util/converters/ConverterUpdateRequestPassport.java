package com.airline.util.converters;

import com.airline.controller.request.PassportUpdateRequest;
import com.airline.entity.Passports;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

import static java.util.Optional.ofNullable;

@Component
public class ConverterUpdateRequestPassport extends ConverterRequestPassport<PassportUpdateRequest, Passports> {


	@Override
	public Passports convert (PassportUpdateRequest request) {

		Passports passports = ofNullable (entityManager.find (Passports.class, request.getId ()))
				                  .orElseThrow (() -> new EntityNotFoundException ());
		//.orElseThrow(() -> new EntityNotFoundException(Passengers.class, request.getUserId()));
		return doConvert (passports, request);
	}
}