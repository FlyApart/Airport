package com.airport.service;

import com.airport.controller.request.create.TicketsSaveUpdateRequest;
import com.airport.entity.Tickets;

public interface TicketsService {
	Tickets saveAndUpdate (TicketsSaveUpdateRequest ticket);

}
