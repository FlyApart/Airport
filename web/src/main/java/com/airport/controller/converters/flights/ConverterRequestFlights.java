package com.airport.controller.converters.flights;

import com.airport.controller.converters.EntityConverter;
import com.airport.controller.request.change.FlightsUpdateRequest;
import com.airport.controller.request.create.FlightsSaveRequest;
import com.airport.entity.Airline;
import com.airport.entity.Airplanes;
import com.airport.entity.Airports;
import com.airport.entity.Discounts;
import com.airport.entity.Flights;
import com.airport.exceptions.ArgumentOfMethodNotValidException;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityAlreadyExistException;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.AirlinesRepository;
import com.airport.repository.springdata.AirplanesRepository;
import com.airport.repository.springdata.AirportsRepository;
import com.airport.repository.springdata.DiscountsRepository;
import com.airport.repository.springdata.FlightsRepository;
import com.airport.util.ProjectDate;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public abstract class ConverterRequestFlights<S, T> extends EntityConverter<S, T> {

	private final FlightsRepository flightsRepository;
	private final AirportsRepository airportsRepository;
	private final AirplanesRepository airplanesRepository;
	private final AirlinesRepository airlinesRepository;
	private final DiscountsRepository discountsRepository;

	Flights doConvert (Flights flights, FlightsSaveRequest entity) {
		flights.setFightsNumber (entity.getFightsNumber ());
		flights.setArriveDate (entity.getArriveDate ());
		flights.setDepartureDate (entity.getDepartureDate ());
		flights.setPrice (Double.valueOf (entity.getPrice ()));
		flights.setFightsNumber (entity.getFightsNumber ());
		validAirport (entity.getClass (), flights);
		validDate (entity.getClass (), flights);

		return flights;
	}

	Flights doConvert (Flights flights, FlightsUpdateRequest entity) {

		if (entity.getFightsNumber () != null) {
			flights.setFightsNumber (entity.getFightsNumber ());
		}

		if (entity.getArriveDate () != null) {
			flights.setArriveDate (entity.getArriveDate ());
		}

		if (entity.getDepartureDate () != null) {
			flights.setDepartureDate (entity.getDepartureDate ());
		}

		if (entity.getPrice () != null){
			flights.setPrice (Double.valueOf (entity.getPrice ()));
		}

		validAirport (entity.getClass (), flights);

		validDate (entity.getClass (), flights);

		flights.setChanged (new ProjectDate ().getCurrentTime ());

		return flights;
	}

	void validAirport (Class<?> sClass, Flights flights) {
		if (flights.getArriveAirport () == flights.getDepartureAirport ()) {
			throw new ConversionException (sClass, Flights.class, flights,
					new ArgumentOfMethodNotValidException (Flights.class, "airport arrive " + flights.getArriveAirport () + " = " + "departure " + flights.getDepartureAirport ()));
		}
	}

	void validDate (Class<?> sClass, Flights flights) {
		if (flights.getArriveDate ()
		           .compareTo (flights.getDepartureDate ()) < 0) {
			throw new ConversionException (sClass, Flights.class, flights,
					new ArgumentOfMethodNotValidException (Flights.class, "Date arrive" + flights.getArriveDate () + " <= " + "departure " + flights.getDepartureDate ()));
		}
	}

	void uniqueFlightNumber (Class<?> sClass, String num) {
		boolean unique = flightsRepository.findByFightsNumber (num)
		                                  .isPresent ();
		if(unique){
			throw new ConversionException (sClass, Flights.class, num, new EntityAlreadyExistException (Flights.class, " flights number= " + num));
		}
	}

	Flights findFlights (Class<?> sClass, Long id) {
		return flightsRepository.findById (id)
		                          .orElseThrow (() -> new ConversionException (sClass, Flights.class, id, new EntityNotFoundException (Flights.class, id)));
	}

	Airline findAirline (Class<?> sClass, String name) {
		return airlinesRepository.findByName (name)
		                         .orElseThrow (()-> new ConversionException (sClass, Flights.class, name, new EntityNotFoundException (" name = " + name, Airline.class)));

	}

	Airplanes findAirplanes (Class<?> sClass, Long id) {
		return airplanesRepository.findById (id)
		               .orElseThrow (() -> new ConversionException (sClass, Flights.class, id, new EntityNotFoundException (Airplanes.class, id)));
	}

	Airports findAirport (Class<?> sClass, String title) {
		return airportsRepository.findByTitle (title)
		                         .orElseThrow (() -> new ConversionException (sClass, Flights.class, title, new EntityNotFoundException (" title = " + title, Airports.class)));
	}

	Set<Discounts> findDiscounts (Class<?> sClass, Set<Long> discountId) {
		List<Discounts> discountsList = discountsRepository.findByIds(new ArrayList<> (discountId))
		                                             .orElseThrow (()->new ConversionException (sClass, Flights.class, discountId, new EntityNotFoundException (Discounts.class, discountId)));
		if (discountId.size ()!=discountsList.size ()){
			for (Discounts discounts : discountsList) {
				discountId.remove (discounts.getId ());
			}
			throw new ConversionException (sClass, Flights.class, discountId, new EntityNotFoundException (Discounts.class, discountId));
		}
		return new HashSet<> (discountsList);
	}
}
