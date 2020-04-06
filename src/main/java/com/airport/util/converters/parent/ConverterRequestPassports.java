package com.airport.util.converters.parent;

import com.airport.controller.request.save.PassportSaveRequest;
import com.airport.controller.request.update.PassportUpdateRequest;
import com.airport.entity.Passports;

public abstract class ConverterRequestPassports<S, T> extends EntityConverter<S, T>  {

	protected Passports doConvert (Passports passports, PassportSaveRequest entity) {
		passports.setTypes (entity.getTypes ());
		passports.setSeries (Long.valueOf (entity.getSeries ()));
		passports.setNumber (Long.valueOf(entity.getNumber ()));
		return passports;
	}

	protected Passports doConvert (Passports passports, PassportUpdateRequest entity) {
		//passports.setTitle (entity.getTitle ());
		passports.setSeries (Long.valueOf(entity.getSeries ()));
		passports.setNumber (Long.valueOf(entity.getNumber ()));
		return passports;
	}
}
