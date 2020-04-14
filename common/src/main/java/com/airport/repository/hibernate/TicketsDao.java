package com.airport.repository.hibernate;


import com.airport.entity.Tickets;

import java.util.List;
import java.util.Set;


public interface TicketsDao extends GenericDao <Tickets, Long> {
	Set<Tickets> findByIds(List<Long> ticketIds);
}
