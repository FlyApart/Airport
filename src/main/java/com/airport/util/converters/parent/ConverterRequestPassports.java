package com.airport.util.converters.parent;

import com.airport.controller.request.change.PassportUpdateRequest;
import com.airport.controller.request.create.PassportSaveRequest;
import com.airport.entity.Passports;

public abstract class ConverterRequestPassports<S, T> extends EntityConverter<S, T>  {

	protected Passports doConvert (Passports passports, PassportSaveRequest entity) {
		passports.setTypes (entity.getTypes ());
		passports.setSeries (Long.valueOf (entity.getSeries ()));
		passports.setNumber (Long.valueOf(entity.getNumber ()));
		return passports;
	}

	protected Passports doConvert (Passports passports, PassportUpdateRequest entity) {
		passports.setTypes (entity.getTypes ());
		passports.setSeries (Long.valueOf(entity.getSeries ()));
		passports.setNumber (Long.valueOf(entity.getNumber ()));
		return passports;
	}
}
