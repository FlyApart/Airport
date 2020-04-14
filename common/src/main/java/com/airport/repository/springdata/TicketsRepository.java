package com.airport.repository.springdata;


import com.airport.entity.SeatsClass;
import com.airport.entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface TicketsRepository extends CrudRepository<Tickets, Long>, JpaRepository<Tickets, Long> {
	@Modifying
	@Query("delete  from Tickets t where t = :tickets")
	void deleteTickets(Tickets tickets);


	@Query("select t.place from Tickets t where t.flights.id= :flightsId and t.seatsClass= :classSeat order by t.place asc ")
    Optional<List<String>> findPlacesByFlights(Long flightsId, SeatsClass classSeat);

    @Query("select t from Tickets t where t.flights.id= :flightsId and t.place= :place")
    Optional<Tickets> findTicketsByFlightsIdAndPlace(Long flightsId, String place);
}
