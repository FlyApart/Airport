package com.airport.repository.springdata;


import com.airport.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface AirlinesRepository extends CrudRepository<Airline, Long>, JpaRepository<Airline, Long> {

	Optional<Airline> findByNameAndWebsite (String name, String website);
	Optional<Airline> findByName (String name);

	@Modifying
	@Query("delete  from Airline a where a = :airline")
	void deleteAirlines (Airline airline);
}