package com.airport.service.impl;

import com.airport.entity.Airplanes;
import com.airport.entity.Flights;
import com.airport.entity.Tickets;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.FlightsRepository;
import com.airport.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

private final FlightsRepository flightsRepository;

	@Value(value = "${character_of_place}")
	private String PLACE;

	@Override
	public Flights deleteFlightDiscounts (String id, List<Long> discountIds) {

		Flights flights = flightsRepository.findById (Long.valueOf (id))
		                                   .orElseThrow (()-> new EntityNotFoundException (Flights.class, id));

		for (Long discountId : discountIds) {
			flights.getDiscount ()
			       .removeIf (discounts -> discountId.equals (discounts.getId ()));
		}

		return flightsRepository.saveAndFlush (flights);
	}

	@Override
	public List<Flights> findByParam (Flights request, Pageable pageable) {

		String arriveCity = request.getArriveAirport ().getCities ().getName ();

		String departureCity = request.getDepartureAirport ().getCities ().getName ();

		Optional<List<Flights>> flightsList;

		if (request.getArriveDate ()!=null && !arriveCity.equals ("Any Cities")) {

			 flightsList = flightsRepository.findWithAllParam (
					arriveCity,
					departureCity,
					request.getArriveDate (),
					request.getDepartureDate (),
					 pageable);
		}

		else if (!arriveCity.equals ("Any Cities")){

			flightsList = flightsRepository.findWithNullArriveDateParam (
					arriveCity,
					departureCity,
					request.getDepartureDate (),
					pageable);
		}

		else if (request.getArriveDate ()!=null){

			flightsList = flightsRepository.findWithAnyArriveCitiesParam (
					departureCity,
					request.getArriveDate (),
					request.getDepartureDate (),
					pageable);
		}

		else {
			flightsList = flightsRepository.findWithAnyArriveCitiesAndNullArriveDateParam (
					departureCity,
					request.getDepartureDate (),
					pageable);
		}

		return flightsList.orElseGet (ArrayList::new);
	}

	@Override
	public List<String> findAllOccupiedSeats (Long flightsId, Boolean isEmpty) {

		if (isEmpty){
			Optional<Flights> flight = flightsRepository.findById (flightsId);
			if (flight.isPresent ()){
				Airplanes airplane = flight.get ().getAirplane ();

				List<String> totalSeats = new ArrayList<> ();
				Integer seats = airplane.getSeats ();
				Integer row = airplane.getRow ();
				Integer comfortSeats = airplane.getComfortSeats ();
				Integer comfortRow = airplane.getComfortRow ();
				Integer businessSeats = airplane.getBusinessSeats ();
				Integer businessRow = airplane.getBusinessRow ();

				for (Integer i = 1; i <= seats/row; i++) {
					for (int j = 0; j < row; j++) {
						totalSeats.add (i.toString () + PLACE.charAt (j));
					}
				}
				for (Integer i = seats/row+1; i <= seats/row+comfortSeats/comfortRow+2; i++) {
					for (int j = 0; j < comfortRow; j++) {
						totalSeats.add (i.toString () + PLACE.charAt (j));
					}
				}
				for (Integer i = seats/row+comfortSeats/comfortRow+3; i <= businessSeats/businessRow + seats/row + comfortSeats/comfortRow+2; i++) {
					for (int j = 0; j < businessRow; j++) {
						totalSeats.add (i.toString () + PLACE.charAt (j));
					}
				}

				for (Tickets tickets : flight.get ()
				                             .getTicket ()) {
					totalSeats.removeIf(a->a.equals (tickets.getPlace ()));
				}

				if (!totalSeats.isEmpty ()){
					return totalSeats;
				}
			}
		}
		else {
			Optional<List<String>> seats = flightsRepository.findAllOccupiedSeats (flightsId);
			if (seats.isPresent ()){
				return seats.get ();
			}
		}

		return new ArrayList<> ();
	}
}
