package com.airport.repository.springdata;

import com.airport.entity.Airports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface AirportsRepository extends CrudRepository<Airports, Long>, JpaRepository<Airports, Long> {
	// public Airports findByTitle (String title);
}
