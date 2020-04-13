package com.airport.controller.converters.flights;

import com.airport.controller.converters.EntityConverter;
import com.airport.controller.exceptions.ArgumentOfMethodNotValidException;
import com.airport.controller.exceptions.ConversionException;
import com.airport.controller.exceptions.EntityAlreadyExistException;
import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.change.FlightsUpdateRequest;
import com.airport.controller.request.create.FlightsSaveRequest;
import com.airport.entity.Airlines;
import com.airport.entity.Airplanes;
import com.airport.entity.Airports;
import com.airport.entity.Flights;
import com.airport.util.ProjectDate;

import javax.persistence.NoResultException;
import java.util.Optional;

public abstract class ConverterRequestFlights<S, T> extends EntityConverter<S, T> {

	Flights doConvert (Flights flights, FlightsSaveRequest entity) {
		flights.setFightsNumber (entity.getFightsNumber ());
		flights.setArriveDate (entity.getArriveDate ());
		flights.setDepartureDate (entity.getDepartureDate ());
		flights.setPrice (Double.valueOf (entity.getPrice ()));
		validAirport (entity.getClass (),flights);
		validDate (entity.getClass (),flights);
		validNumber (entity.getClass (),flights);
		return flights;
	}

	Flights doConvert (Flights flights, FlightsUpdateRequest entity) {
		if (entity.getFightsNumber () != null) flights.setFightsNumber (entity.getFightsNumber ());
		if (entity.getArriveDate () != null) flights.setArriveDate (entity.getArriveDate ());
		if (entity.getDepartureDate () != null) flights.setDepartureDate (entity.getDepartureDate ());
		if (entity.getPrice () != null) flights.setPrice (Double.valueOf (entity.getPrice ()));
		validAirport (entity.getClass (),flights);
		validDate (entity.getClass (),flights);
		if (flights.getFightsNumber ()!=entity.getFightsNumber ( )&& entity.getFightsNumber ()!=null) {
			validNumber (entity.getClass (), flights);
		}
		flights.setChanged (new ProjectDate ().getCurrentTime ());
		return flights;
	}

	void validNumber (Class<?> sClass, Flights flights){
		try {
			entityManager.createQuery ("from Flights f where f.fightsNumber=: title",Flights.class)
			                        .setParameter ("title",flights.getFightsNumber ()).getSingleResult ();
		}catch (NoResultException e) {
			return;
		}
		throw new ConversionException (sClass, Flights.class, flights, new EntityAlreadyExistException (Flights.class," flights number= " + flights.getFightsNumber ()));
	}

	void validAirport (Class<?> sClass, Flights flights){
		if (flights.getArriveAirport ()==flights.getDepartureAirport ()){
			throw new ConversionException (FlightsSaveRequest.class, Flights.class,flights,
					new ArgumentOfMethodNotValidException (Flights.class, "airport arrive"+flights.getArriveAirport()+" = " +
							                                                      "departure "+flights.getDepartureAirport () ));
		}
	}

	void validDate (Class<?> sClass, Flights flights){
		if (flights.getArriveDate ().compareTo (flights.getDepartureDate ())<0){
			throw new ConversionException (FlightsSaveRequest.class, Flights.class, null,
					new ArgumentOfMethodNotValidException (Flights.class, "Date arrive"+flights.getArriveDate()+" <= " +
							                                                      "departure "+flights.getDepartureDate () ));
		}
	}

	Airlines findAirline (Class<?> sClass, String name) {
		try {
		return 	 entityManager.createQuery ("from Airlines a where a.name=: name",Airlines.class)
		                                             .setParameter ("name",name).getSingleResult ();
		}catch (NoResultException e) {
			throw new ConversionException (sClass, Flights.class, name, new EntityNotFoundException (" name = " + name, Airlines.class));
		}
	}

	Airplanes findAirplanes (Class<?> sClass, Long id) {
		return Optional.ofNullable (entityManager.find (Airplanes.class, id))
		               .orElseThrow (() -> new ConversionException (sClass, Flights.class, id, new EntityNotFoundException (Airplanes.class, id)));
	}

	Airports findAirport (Class<?> sClass, String title) {
		try {
			return 	 entityManager.createQuery ("from Airports a where a.title=: title",Airports.class)
			                        .setParameter ("title",title).getSingleResult ();
		}catch (NoResultException e) {
			throw new ConversionException (sClass, Flights.class, title, new EntityNotFoundException (" title = " + title, Airports.class));
		}
	}

}
