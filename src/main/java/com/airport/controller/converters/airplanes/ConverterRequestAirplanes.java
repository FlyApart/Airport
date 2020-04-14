package com.airport.controller.converters.airplanes;

import com.airport.controller.converters.EntityConverter;
import com.airport.controller.request.change.AirplanesUpdateRequest;
import com.airport.controller.request.create.AirplanesSaveRequest;
import com.airport.entity.Airplanes;
import com.airport.entity.Countries;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityAlreadyExistException;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.AirplanesRepository;
import com.airport.repository.springdata.CountriesRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ConverterRequestAirplanes<S, T> extends EntityConverter<S, T> {

	private final CountriesRepository countriesRepository;
	private final AirplanesRepository airplanesRepository;

	protected Airplanes doConvert (Airplanes airplanes, AirplanesSaveRequest entity) {
		airplanes.setBuilt (entity.getBuilt ());
		airplanes.setFlightDuration (Long.valueOf (entity.getFlightDuration ()));
		airplanes.setModel (entity.getModel ());
		airplanes.setSeats (Integer.valueOf (entity.getSeats ()));
		airplanes.setRow (Integer.valueOf (entity.getRow ()));
		airplanes.setComfortSeats (Integer.valueOf (entity.getComfortSeats ()));
		airplanes.setComfortRow (Integer.valueOf (entity.getComfortRow ()));
		airplanes.setBusinessRow (Integer.valueOf (entity.getBusinessSeats ()));
		airplanes.setBusinessRow (Integer.valueOf (entity.getBusinessRow ()));
		return airplanes;
	}

	protected Airplanes doConvert (Airplanes airplanes, AirplanesUpdateRequest entity) {
		if (entity.getBuilt () != null) airplanes.setBuilt (entity.getBuilt ());
		if (entity.getFlightDuration () != null)
			airplanes.setFlightDuration (Long.valueOf (entity.getFlightDuration ()));
		if (entity.getModel () != null) airplanes.setModel (entity.getModel ());
		if (entity.getSeats () != null) airplanes.setSeats (Integer.valueOf (entity.getSeats ()));
		if (entity.getRow () != null) airplanes.setRow (Integer.valueOf (entity.getRow ()));
		if (entity.getComfortSeats () != null) airplanes.setComfortSeats (Integer.valueOf (entity.getComfortSeats ()));
		if (entity.getComfortRow () != null) airplanes.setComfortRow (Integer.valueOf (entity.getComfortRow ()));
		if (entity.getBusinessSeats () != null) airplanes.setBusinessRow (Integer.valueOf (entity.getBusinessSeats ()));
		if (entity.getBusinessRow () != null) airplanes.setBusinessRow (Integer.valueOf (entity.getBusinessRow ()));
		return airplanes;
	}

	Countries findCountries (Class<?> sClass, String country) {
		return countriesRepository.findByName (country)
		                          .orElseThrow (() -> new ConversionException (sClass, Airplanes.class, country, new EntityNotFoundException (" name = " + country, Countries.class)));

	}


	void isUniqueModelAirplanes (Class<?> sClass, String model) {

		boolean unique = airplanesRepository.findByModel (model)
		                                   .isPresent ();
		if (unique){
			throw new ConversionException (sClass, Airplanes.class, model, new EntityAlreadyExistException (Airplanes.class, "model = " + model));
		}
		else
			return;
	}
}
