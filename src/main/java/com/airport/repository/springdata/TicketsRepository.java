package com.airport.repository.springdata;


import com.airport.entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface TicketsRepository extends CrudRepository<Tickets, Long>, JpaRepository<Tickets, Long> {
	@Modifying
	@Query("delete  from Tickets t where t = :tickets")
	void deleteTickets(Tickets tickets);


	/*@Query("delete  from Tickets t where t = :tickets")
	void deleteTickets(Tickets tickets);*/

	//Optional<Tickets> findByFlightsAndPassengersIdOrAndFlightsAndPlace (Flights flights, Passengers passengersId, String place);

	/*@Query("select from ")
	void selectUniquePassengerFlightsAndPlace(Tickets tickets);*/
}
