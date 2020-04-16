package com.airport.controller.converters.tickets;

import com.airport.controller.request.create.TicketsSaveUpdateRequest;
import com.airport.entity.Tickets;
import org.springframework.stereotype.Component;

@Component
public class ConverterSaveRequestTickets extends ConverterRequestTickets<TicketsSaveUpdateRequest, Tickets> {

	@Override
	public Tickets convert (TicketsSaveUpdateRequest request) {

		return doConvert (new Tickets (), request);
	}
}
