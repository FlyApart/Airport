package com.airport.repository.springdata;


import com.airport.entity.Airline;
import com.airport.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface AirlinesRepository extends CrudRepository<Airline, Long>, JpaRepository<Airline, Long> {

	Optional<Airline> findByNameAndWebsite (String name, String website);

	Optional<Airline> findByNameIgnoreCase (String name);

	@Query(value = "select a from Airline a where a.status = :status")
	Optional<Page<Airline>>findByStatus(Status status, Pageable pageable );

}