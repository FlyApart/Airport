package com.airport.service.impl;

import com.airport.controller.exceptions.EntityAlreadyExistException;
import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.create.SaveAndUpdateTicketsRequest;
import com.airport.entity.*;
import com.airport.repository.springdata.FlightsRepository;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.repository.springdata.TicketsRepository;
import com.airport.service.TicketsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketsServiceImpl implements TicketsService {

	private final FlightsRepository flightsRepository;
	private final PassengersRepository passengersRepository;
	private final TicketsRepository ticketsRepository;

	Flights findFlight (Long flightId) {
		return flightsRepository.findById (flightId)
		                        .orElseThrow (() -> new EntityNotFoundException (Flights.class, flightId));
	}

	Passengers findPassengers (Long passengerId) {
		return passengersRepository.findById (passengerId)
		                           .orElseThrow (() -> new EntityNotFoundException (Passengers.class, passengerId));
	}

	Double costCalculation (Tickets tickets, String ticketDiscount) {
		if (ticketDiscount != null) {
			for (Discounts discounts : tickets.getFlights ()
			                                  .getDiscount ()) {
				if (discounts.getTitle ()
				             .equals (ticketDiscount)) {
					return tickets.getFlights ()
					              .getPrice () - discounts.getCost ();
				}
			}
			throw new EntityNotFoundException ("discount name = " + ticketDiscount, Flights.class);
		}
		return tickets.getFlights ()
		              .getPrice ();
	}

	void uniquePassengersAndFlights (Tickets tickets) {
		Long id = tickets.getFlights ()
		                 .getId ();
		Passengers passengersId = tickets.getPassengersId ();
		for (Tickets ticket : passengersId.getTicket ()) {
			if (ticket.getFlights ()
			          .getId () == id) {
				throw new EntityAlreadyExistException (Tickets.class, "passenger = " + passengersId + " Flights = " + tickets.getFlights ());
			}
		}
	}

	String getPlaces (Tickets tickets, SaveAndUpdateTicketsRequest ticketRequest) {

		Long row;
		Long seats;


		if (FlightsClass.NORMAL.equals (tickets.getFlightsClass ())) {
			row = tickets.getFlights ().getAirplane ().getRow ();
			seats = tickets.getFlights ().getAirplane ().getSeats ();
		} else if (FlightsClass.BUSINESS.equals (tickets.getFlightsClass ())) {
			row = tickets.getFlights ().getAirplane ().getBusinessRow ();
			seats = tickets.getFlights ().getAirplane ().getBusinessSeats ();
		} else if (FlightsClass.COMFORT.equals (tickets.getFlightsClass ())) {
			row = tickets.getFlights ().getAirplane ().getComfortRow ();
			seats = tickets.getFlights ().getAirplane ().getComfortSeats ();
		} else throw new EntityNotFoundException (" Class seats = " + tickets.getFlightsClass (), Tickets.class);


	}
450
300 10  30
100	5   20
50	2   25
	String creater (Long seats, Long row, String place) {
		Integer numberSeat = Integer.valueOf (place.substring (0,place.length ()-1));
		String characterSeat = place.substring (place.length ()-1);
		String s = "ABCDEFGHIJKL";
		String substring = s.substring (0, (int) (row - 1));
		for (int i = 0; i < substring.length (); i++) {
			equals (substring.charAt (i),place.charAt (place.length ()-1));
			substring.charAt (i).
		}


		return
	}




	@Override
	public Tickets saveAndUpdate (SaveAndUpdateTicketsRequest ticketRequest) {
		Tickets tickets = new Tickets ();

		if (ticketRequest.getId () != null) {
			tickets = ticketsRepository.findById (Long.valueOf (ticketRequest.getId ()))
			                           .orElseThrow (() -> new EntityNotFoundException (Tickets.class, ticketRequest.getId ()));
		}

		tickets.setFlightsClass (ticketRequest.getFlightsClass ());

		tickets.setFlights (findFlight (Long.valueOf (ticketRequest.getFlightsID ())));

		tickets.setPassengersId (findPassengers (Long.valueOf (ticketRequest.getPassengersId ())));

		uniquePassengersAndFlights (tickets);

		tickets.setTotalPrice (costCalculation (tickets, ticketRequest.getDiscountsTitle ()));


		tickets.setPlace (ticketRequest.getPlace ());//TODO fix this in service // 1 query to place and unique pass- flights

		tickets.setReservation (false);//TODO fix this in service

		return ticketsRepository.saveAndFlush (tickets);

	}
}
