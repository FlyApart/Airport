package com.airport.service;

import com.airport.controller.request.create.SaveAndUpdateTicketsRequest;
import com.airport.entity.Tickets;

public interface TicketsService {
	Tickets saveAndUpdate(SaveAndUpdateTicketsRequest ticket);
}
