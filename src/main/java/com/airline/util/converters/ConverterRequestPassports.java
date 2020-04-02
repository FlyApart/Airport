package com.airline.util.converters;

import com.airline.controller.request.PassportSaveRequest;
import com.airline.entity.Passports;

public abstract class ConverterRequestPassports<S, T> extends EntityConverter<S, T>  {


	protected Passports doConvert (Passports passports, PassportSaveRequest entity) {
		passports.setTitle (entity.getTitle ());
		passports.setSeries (entity.getSeries ());
		passports.setNumber (entity.getNumber ());
		return passports;
	}
}
