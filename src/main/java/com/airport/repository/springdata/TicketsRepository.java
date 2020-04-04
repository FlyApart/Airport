package com.airport.repository.springdata;


import com.airport.entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface TicketsRepository extends CrudRepository<Tickets, Long>, JpaRepository<Tickets, Long> {
	//Set<com.airline.entity.Tickets> findById (List<Long> ticketIds);
}
