package com.airline.util.converters;

import com.airline.controller.request.PassportRequest;
import com.airline.entity.Passports;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConverterRequestPassport implements Converter<PassportRequest, Passports> {

	@Override
	public Passports convert (PassportRequest entity) {
		Passports passports = new Passports ();
		passports.setTitle (entity.getTitle ());
		passports.setSeries (entity.getSeries ());
		passports.setNumber (entity.getNumber ());
		return passports;
	}

	public Passports convertUpdate (PassportRequest entity, Passports passports){
		passports.setSeries (entity.getSeries ());
		passports.setNumber (entity.getNumber ());
		return passports;
	}
}
