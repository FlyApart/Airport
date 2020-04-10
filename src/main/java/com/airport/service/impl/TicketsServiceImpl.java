package com.airport.service.impl;

import com.airport.controller.exceptions.ArgumentOfMethodNotValidException;
import com.airport.controller.exceptions.EntityAlreadyExistException;
import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.create.SaveAndUpdateTicketsRequest;
import com.airport.entity.*;
import com.airport.repository.springdata.FlightsRepository;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.repository.springdata.TicketsRepository;
import com.airport.service.TicketsService;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

/*
	String getPlaces (Tickets tickets, SaveAndUpdateTicketsRequest ticketRequest) {
        Integer minSeatsNum;
        Integer maxSeatsNum;
		Long row;
		Long seats;

		if (FlightsClass.NORMAL.equals (tickets.getFlightsClass ())) {
            	seats = tickets.getFlights ().getAirplane ().getSeats ();
            if(seats==null) throw new ArgumentOfMethodNotValidException ("Airplane "+tickets.getFlights ().getAirplane ()+", class "+tickets.getFlightsClass ());
            	row = tickets.getFlights ().getAirplane ().getRow ();
            	minSeatsNum = 1;
               maxSeatsNum = (int)(seats/row)+minSeatsNum;
		} else if (FlightsClass.COMFORT.equals (tickets.getFlightsClass ())) {
            seats = tickets.getFlights ().getAirplane ().getComfortSeats ();
            if(seats==null) throw new ArgumentOfMethodNotValidException ("Airplane "+tickets.getFlights ().getAirplane ()+", class "+tickets.getFlightsClass ());
            	row = tickets.getFlights ().getAirplane ().getComfortRow ();
                minSeatsNum = (int)((tickets.getFlights ().getAirplane ().getSeats ()/tickets.getFlights ().getAirplane ().getRow ())+2);
                maxSeatsNum = (int)(seats/row)+minSeatsNum;
        } else if (FlightsClass.BUSINESS.equals (tickets.getFlightsClass ())) {
                seats = tickets.getFlights ().getAirplane ().getBusinessSeats ();
            if(seats==null) throw new ArgumentOfMethodNotValidException ("Airplane "+tickets.getFlights ().getAirplane ()+", class "+tickets.getFlightsClass ());
                row = tickets.getFlights ().getAirplane ().getBusinessRow ();
                minSeatsNum = (int)((tickets.getFlights ().getAirplane ().getSeats ()/tickets.getFlights ().getAirplane ().getRow ()+
                    tickets.getFlights ().getAirplane ().getComfortSeats ()/tickets.getFlights ().getAirplane ().getComfortRow ())+3);
                maxSeatsNum = (int)(seats/row)+minSeatsNum;
        } else throw new EntityNotFoundException (" Class seats = " + tickets.getFlightsClass (), Tickets.class);
		
        

	}
450
300 10  30  1A 1B 1C 1D 1E 1F 1G 1H 1I 1J
100	5   20
50	2   25
	String creater (Long seats, Long row, Integer minSeatsNum, Integer  maxSeatsNum, String place, Tickets ticket) {

        if(place!=null){
            Integer numberSeat = Integer.valueOf (place.substring (0,place.length ()-1));
            CharSequence charSequence = place.substring(place.length());
            if(numberSeat>=minSeatsNum&&numberSeat<=maxSeatsNum){
              if(!ticketsRepository.findTicketsByFlightsIdAndPlace(ticket.getFlights().getId(),place).isEmpty()){
                  throw new EntityAlreadyExistException (Tickets.class, "place = " + place + ", Flights id = " + ticket.getFlights ().getId());
              }
            }else throw new ArgumentOfMethodNotValidException ("Place "+place+", class "+ticket.getFlightsClass ());
		}
        else {
           List<String> placeInTicket =
                   ticketsRepository.findPlacesByFlights(ticket.getFlights().getId(),ticket.getFlightsClass()).get();
            if (placeInTicket.isEmpty()){
                return minSeatsNum.toString().concat("A");
                }
            else {
                for (String s : placeInTicket) {
                    
                }
                }
        }
        return place;
		String characterSeat = place.substring (place.length ()-1);
		String s = "ABCDEFGHIJKL";
		String substring = s.substring (0, (int) (row - 1));
		for (int i = 0; i < substring.length (); i++) {
			substring.charAt (i);
		}


		return
                }
	}

*/



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
