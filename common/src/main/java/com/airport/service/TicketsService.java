package com.airport.service;

import com.airport.entity.Tickets;

public interface TicketsService {
	Tickets saveAndUpdate (Tickets ticket);

	String reservation (String code);

}
