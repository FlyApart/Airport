package com.airport.util.converters.passports;

import com.airport.controller.request.change.PassportUpdateRequest;
import com.airport.controller.request.create.PassportSaveRequest;
import com.airport.entity.Passports;
import com.airport.util.converters.EntityConverter;

public abstract class ConverterRequestPassports<S, T> extends EntityConverter<S, T> {

	protected Passports doConvert (Passports passports, PassportSaveRequest entity) {
		passports.setTypes (entity.getTypes ());
		passports.setSeries (Long.valueOf (entity.getSeries ()));
		passports.setNumber (Long.valueOf(entity.getNumber ()));
		return passports;
	}

	protected Passports doConvert (Passports passports, PassportUpdateRequest entity) {
	    if (entity.getNumber()!=null) passports.setNumber (Long.valueOf(entity.getNumber ()));
	    if (entity.getTypes()!=null) passports.setTypes (entity.getTypes ());
	    if (entity.getSeries()!=null) passports.setSeries (Long.valueOf(entity.getSeries ()));

		return passports;
	}
}
