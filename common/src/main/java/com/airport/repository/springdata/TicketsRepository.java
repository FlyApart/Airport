package com.airport.repository.springdata;


import com.airport.entity.SeatsClass;
import com.airport.entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;


public interface TicketsRepository extends CrudRepository<Tickets, Long>, JpaRepository<Tickets, Long>, QueryByExampleExecutor<Tickets> {

	@Query("select t.place from Tickets t where t.flights.id= :flightsId and t.seatsClass= :classSeat order by t.place asc ")
	Optional<List<String>> findPlacesByFlights (Long flightsId, SeatsClass classSeat);

	Optional<Tickets> findByPlaceAndFlights_Id (String place, Long flightsId);

	Optional<Tickets> findByActivationCode (String code);

}
