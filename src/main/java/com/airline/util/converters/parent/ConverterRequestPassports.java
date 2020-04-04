package com.airline.util.converters.parent;

import com.airline.controller.request.PassportSaveRequest;
import com.airline.controller.request.PassportUpdateRequest;
import com.airline.entity.Passports;

public abstract class ConverterRequestPassports<S, T> extends EntityConverter<S, T>  {


	protected Passports doConvert (Passports passports, PassportSaveRequest entity) {
		passports.setTitle (entity.getTitle ());
		passports.setSeries (Long.valueOf (entity.getSeries ()));
		passports.setNumber (Long.valueOf(entity.getNumber ()));
		return passports;
	}

	protected Passports doConvert (Passports passports, PassportUpdateRequest entity) {
		passports.setTitle (entity.getTitle ());
		passports.setSeries (Long.valueOf(entity.getSeries ()));
		passports.setNumber (Long.valueOf(entity.getNumber ()));
		return passports;
	}
}
