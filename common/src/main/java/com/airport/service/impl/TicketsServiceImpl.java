package com.airport.service.impl;

import com.airport.entity.Airplanes;
import com.airport.entity.Discounts;
import com.airport.entity.Flights;
import com.airport.entity.Passenger;
import com.airport.entity.SeatsClass;
import com.airport.entity.Tickets;
import com.airport.exceptions.ArgumentOfMethodNotValidException;
import com.airport.exceptions.CustomException;
import com.airport.exceptions.EntityAlreadyExistException;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.FlightsRepository;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.repository.springdata.TicketsRepository;
import com.airport.service.TicketsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketsServiceImpl implements TicketsService {

	private final FlightsRepository flightsRepository;
	private final PassengersRepository passengersRepository;
	private final TicketsRepository ticketsRepository;
	private final MailSenderService mailSenderService;

	@Value(value = "${character_of_place}")
	private String PLACE;

	public Flights findFlight (Long flightId) {
		return flightsRepository.findById (flightId)
		                        .orElseThrow (() -> new EntityNotFoundException (Flights.class, flightId));
	}

	public Passenger findPassengers (Long passengerId) {
		return passengersRepository.findById (passengerId)
		                           .orElseThrow (() -> new EntityNotFoundException (Passenger.class, passengerId));
	}

	public Double costCalculation (Tickets tickets, String ticketDiscount) {
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

	public void uniquePassengersAndFlights (Tickets tickets) {
		Long id = tickets.getFlights ()
		                 .getId ();
		Passenger passengerId = tickets.getPassengerId ();
		for (Tickets ticket : passengerId.getTicket ()) {
			if (ticket.getFlights ()
			          .getId ()
			          .equals (id)) {
				throw new EntityAlreadyExistException (Tickets.class, "passenger = " + passengerId + " Flights = " + tickets.getFlights ());
			}
		}
	}

	public String generatePlace(Integer row, Integer minSeatsNum, Integer maxSeatsNum, Tickets ticket){

		Airplanes airplane = ticket.getFlights ()
		                           .getAirplane ();

		List<String> placeInTicket = ticketsRepository.findPlacesByFlights (ticket.getFlights ()
		                                                                          .getId (), ticket.getSeatsClass ())
		                                              .orElse (null);

		if (placeInTicket == null) {
			return minSeatsNum.toString ()
			                  .concat ("A");

		} else if (placeInTicket.size () == airplane.getSeats () +
				                                    airplane.getComfortSeats () +
				                                    airplane.getBusinessSeats ()){

			throw new CustomException ("unable to create Ticket with class = " + ticket.getSeatsClass () + " (out of place)");

		} else {

			List<String> allPlace = new ArrayList<> ();
			for (Integer i = minSeatsNum; i <= maxSeatsNum; i++) {
				for (int j = 0; j < row; j++) {
					allPlace.add (i.toString () + PLACE.charAt (j));
				}
			}
			allPlace.removeAll (placeInTicket);
			return allPlace.get (0);
		}
	}

	public String getPlaces (Tickets tickets, String place) {

		Integer minSeatsNum;
		Integer maxSeatsNum;
		Integer row;
		Integer seats;

		Airplanes airplane = tickets.getFlights ()
		                            .getAirplane ();

		if (SeatsClass.NORMAL.equals (tickets.getSeatsClass ())) {

			seats = airplane.getSeats ();
			row = airplane.getRow ();
			minSeatsNum = 1;
			maxSeatsNum = seats / row + minSeatsNum;

		} else if (SeatsClass.COMFORT.equals (tickets.getSeatsClass ())) {

			seats = airplane.getComfortSeats ();
			row = airplane.getComfortRow ();
			minSeatsNum = airplane.getSeats ()/ airplane.getRow () + 2;
			maxSeatsNum = seats / row + minSeatsNum;

		} else if (SeatsClass.BUSINESS.equals (tickets.getSeatsClass ())) {

			seats = airplane.getBusinessSeats ();
			row = airplane.getBusinessRow ();
			minSeatsNum = (airplane.getSeats ()
					               / airplane.getRow ()
					               + airplane.getComfortSeats ()
							                 / airplane.getComfortRow () + 3);
			maxSeatsNum = seats / row + minSeatsNum;

		}
		else throw new EntityNotFoundException (" Class seats = " + tickets.getSeatsClass (), Tickets.class);

		if (seats == null) {
			throw new CustomException ("Airplane " + airplane + " does not have " + tickets.getSeatsClass () + " class");
		}

		return getEmptySeat (row, minSeatsNum, maxSeatsNum, place, tickets);

	}

	public String getEmptySeat (Integer row, Integer minSeatsNum, Integer maxSeatsNum, String place, Tickets ticket) {

		if (place != null) {
			Integer numberSeat = Integer.valueOf (place.substring (0, place.length () - 1));
			String charSeat = place.substring (place.length () - 1);


			if (numberSeat >= minSeatsNum && numberSeat <= maxSeatsNum && PLACE.substring (0, row - 1)
			                                                                            .contains (charSeat)) {

				if (ticketsRepository.findByPlaceAndFlights_Id (place, ticket.getFlights ()
				                                                            .getId ())
				                     .isPresent ()) {

					throw new EntityAlreadyExistException (Tickets.class, "place = " + place + ", Flights id = " + ticket.getFlights ()
					                                                                                                     .getId ());
				}
				return place;
			}
				throw new ArgumentOfMethodNotValidException (Tickets.class, "Place " + place + ", class " + ticket.getSeatsClass ());

		} else {
			return generatePlace(row,minSeatsNum,maxSeatsNum,ticket);
		}
	}


	@Override
	public Tickets saveAndUpdate (Tickets ticketRequest) {

		Tickets tickets = new Tickets ();

		boolean uniquePas = true;
		boolean uniqueFlight = true;

		if (ticketRequest.getId () != null) {

			tickets = ticketsRepository.findById (ticketRequest.getId ())
			                           .orElseThrow (() -> new EntityNotFoundException (Tickets.class, ticketRequest.getId ()));

			uniquePas = !tickets.getPassengerId ()
			                    .getId ()
			                    .equals (ticketRequest.getPassengerId ().getId ());

			uniqueFlight = !tickets.getFlights ()
			                       .getId ()
			                       .equals (ticketRequest.getFlights ().getId ());
		}

		tickets.setSeatsClass (ticketRequest.getSeatsClass ());

		if (uniqueFlight) {
			tickets.setFlights (findFlight (ticketRequest.getFlights ().getId ()));
		}

		if (uniquePas) {
			tickets.setPassengerId (findPassengers (ticketRequest.getPassengerId ().getId ()));
		}

		if (uniquePas || uniqueFlight) {
			uniquePassengersAndFlights (tickets);
		}

		if (tickets.getPlace ()==null || !tickets.getPlace ().equals (ticketRequest.getPlace ())) {
			tickets.setPlace (getPlaces (tickets, ticketRequest.getPlace ()));
		}
		tickets.setTotalPrice (costCalculation (tickets, ticketRequest.getDiscount ()));

		tickets.setActivationCode (UUID.randomUUID ().toString ());

		String message = String.format ("Hello, %S! \n" +
				                                "Visit next link http://localhost:8080//rest/tickets/reservation/%s",
				tickets.getPassengerId ().getName (),
				tickets.getActivationCode ()
		);

		mailSenderService.sendEmail (tickets.getPassengerId ().getLogin (),"Activation", message);

		return ticketsRepository.saveAndFlush (tickets);

	}

	@Override
	public String reservation (String code) {
		Optional<Tickets> ticket = ticketsRepository.findByActivationCode(code);
		if (ticket.isPresent ()){
			ticket.get ().setReservation (true);
			ticket.get ().setActivationCode (null);
			ticketsRepository.flush ();
			return String.format ("%s, thx for reservation ",
					ticket.get ().getPassengerId ().getName ());
		}
		return "invalid activation code";
	}


}
