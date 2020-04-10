package com.airport.service.impl;

import com.airport.controller.exceptions.ArgumentOfMethodNotValidException;
import com.airport.controller.exceptions.CustomException;
import com.airport.controller.exceptions.EntityAlreadyExistException;
import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.create.TicketsSaveUpdateRequest;
import com.airport.entity.*;
import com.airport.repository.springdata.FlightsRepository;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.repository.springdata.TicketsRepository;
import com.airport.service.TicketsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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



	String getPlaces (Tickets tickets, TicketsSaveUpdateRequest ticketRequest) {

		Integer minSeatsNum;
		Integer maxSeatsNum;
		Long row;
		Long seats;

		if (FlightsClass.NORMAL.equals (tickets.getFlightsClass ())) {
			seats = tickets.getFlights ()
			               .getAirplane ()
			               .getSeats ();
			if (seats == null) throw new CustomException ("Airplane "+tickets.getFlights ().getAirplane ()+" does not have " + tickets.getFlightsClass ()+" class");
			row = tickets.getFlights ()
			             .getAirplane ()
			             .getRow ();
			minSeatsNum = 1;
			maxSeatsNum = (int) (seats / row) + minSeatsNum;
		} else if (FlightsClass.COMFORT.equals (tickets.getFlightsClass ())) {
			seats = tickets.getFlights ()
			               .getAirplane ()
			               .getComfortSeats ();
			if (seats == null) throw new CustomException ("Airplane "+tickets.getFlights ().getAirplane ()+" does not have " + tickets.getFlightsClass ()+" class");
			row = tickets.getFlights ()
			             .getAirplane ()
			             .getComfortRow ();
			minSeatsNum = (int) ((tickets.getFlights ()
			                             .getAirplane ()
			                             .getSeats () / tickets.getFlights ()
			                                                   .getAirplane ()
			                                                   .getRow ()) + 2);
			maxSeatsNum = (int) (seats / row) + minSeatsNum;
		} else if (FlightsClass.BUSINESS.equals (tickets.getFlightsClass ())) {
			seats = tickets.getFlights ()
			               .getAirplane ()
			               .getBusinessSeats ();
			if (seats == null) throw new CustomException ("Airplane "+tickets.getFlights ().getAirplane ()+" does not have " + tickets.getFlightsClass ()+" class");
			row = tickets.getFlights ()
			             .getAirplane ()
			             .getBusinessRow ();
			minSeatsNum = (int) ((tickets.getFlights ()
			                             .getAirplane ()
			                             .getSeats () / tickets.getFlights ()
			                                                   .getAirplane ()
			                                                   .getRow () + tickets.getFlights ()
			                                                                       .getAirplane ()
			                                                                       .getComfortSeats () / tickets.getFlights ()
			                                                                                                    .getAirplane ()
			                                                                                                    .getComfortRow ()) + 3);
			maxSeatsNum = (int) (seats / row) + minSeatsNum;
		} else throw new EntityNotFoundException (" Class seats = " + tickets.getFlightsClass (), Tickets.class);

		return getEmptySeat (row,minSeatsNum,maxSeatsNum,ticketRequest.getPlace (),tickets);

	}

	String getEmptySeat (Long row, Integer minSeatsNum, Integer maxSeatsNum, String place, Tickets ticket) {

		if (place != null) {
			Integer numberSeat = Integer.valueOf (place.substring (0, place.length () - 1));
			String charSeat = place.substring (place.length () - 1);
			String placeCharacter = "ABCDEFGHIJKL";

			if (numberSeat >= minSeatsNum && numberSeat <= maxSeatsNum && placeCharacter.substring (0, (int) (row - 1))
			                                                                            .contains (charSeat)) {

				if (ticketsRepository.findTicketsByFlightsIdAndPlace (ticket.getFlights ().getId (), place).isPresent ()) {

					throw new EntityAlreadyExistException (Tickets.class, "place = " + place + ", Flights id = " + ticket.getFlights ().getId ());
				}

				return place;

			} else
				throw new ArgumentOfMethodNotValidException (Tickets.class,"Place " + place + ", class " + ticket.getFlightsClass ());
		}
		else {

			List<String>placeInTicket=ticketsRepository.findPlacesByFlights (ticket.getFlights ().getId (), ticket.getFlightsClass ()).orElse (null);

			if (placeInTicket == null) {
				return minSeatsNum.toString ()
				                  .concat ("A");

			} else if (placeInTicket.size () == ticket.getFlights ()
			                                          .getAirplane ()
			                                          .getSeats () + ticket.getFlights ()
			                                                               .getAirplane ()
			                                                               .getComfortSeats () + ticket.getFlights ()
			                                                                                           .getAirplane ()
			                                                                                           .getBusinessSeats ()) {

				throw new CustomException ("unable to create Ticket with class = " + ticket.getFlightsClass () + " (out of place)");

			} else {
				String placeCharacter = "ABCDEFGHIJKL";
				List<String> allPlace = new ArrayList<> ();
				for (Integer i = minSeatsNum; i <= maxSeatsNum; i++) {
					for (int j = 0; j < row; j++) {
						allPlace.add (i.toString () + placeCharacter.charAt (j));
					}
				}
				allPlace.removeAll (placeInTicket);
				return allPlace.get (0);
			}
		}
	}




	@Override
	public Tickets saveAndUpdate (TicketsSaveUpdateRequest ticketRequest) {
		Tickets tickets = new Tickets ();

		boolean uniquePas = true;
		boolean uniqueFlight = true;

		if (ticketRequest.getId ()!=null){
			tickets = ticketsRepository.findById (Long.valueOf (ticketRequest.getId ()))
			                                   .orElseThrow (() -> new EntityNotFoundException (Tickets.class, ticketRequest.getId ()));

			uniquePas = tickets.getPassengersId ().getId ()!=Long.valueOf (ticketRequest.getPassengersId ());
			uniqueFlight = tickets.getFlights ().getId ()!=Long.valueOf (ticketRequest.getFlightsID ());
		}

		tickets.setFlightsClass (ticketRequest.getFlightsClass ());

		if(uniqueFlight){
			tickets.setFlights (findFlight (Long.valueOf (ticketRequest.getFlightsID ())));
		}

		if(uniquePas) {
			tickets.setPassengersId (findPassengers (Long.valueOf (ticketRequest.getPassengersId ())));
		}

		if (uniquePas || uniqueFlight){
			uniquePassengersAndFlights (tickets);
		}


		if(tickets.getPlace ()!=ticketRequest.getPlace ()){
			tickets.setPlace (getPlaces (tickets,ticketRequest));
		}

		tickets.setTotalPrice (costCalculation (tickets, ticketRequest.getDiscountsTitle ()));

		tickets.setReservation (false);//TODO fix this in service

		return ticketsRepository.saveAndFlush (tickets);

	}
}
