package com.airport.controller.converters.tickets;

import com.airport.controller.converters.EntityConverter;
import com.airport.controller.request.create.TicketsSaveUpdateRequest;
import com.airport.entity.Flights;
import com.airport.entity.Passenger;
import com.airport.entity.Tickets;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ConverterRequestTickets<S, T> extends EntityConverter<S, T> {

	protected Tickets doConvert (Tickets tickets, TicketsSaveUpdateRequest entity) {

		if (entity.getId()!=null){
			tickets.setId (Long.valueOf (entity.getId ()));
		}

		if (entity.getPlace()!=null){
			tickets.setPlace (entity.getPlace ());
		}

		if (entity.getFlightsID()!=null){
			tickets.setFlights (Flights.builder ()
			                           .id (Long.valueOf (entity.getFlightsID ()))
			                           .build ());
		}

		if (entity.getPassengersId()!=null){
			tickets.setPassengerId (Passenger.builder ()
			                                 .id (Long.valueOf (entity.getPassengersId ()))
			                                 .build ());
		}
		if (entity.getSeatsClass()!=null){
			tickets.setSeatsClass (entity.getSeatsClass ());
		}

		if (entity.getDiscountsTitle()!=null){
			tickets.setDiscount (entity.getDiscountsTitle ());
		}

		return tickets;
	}
}
