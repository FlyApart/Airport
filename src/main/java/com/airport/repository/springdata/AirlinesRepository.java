package com.airport.repository.springdata;


import com.airport.entity.Airlines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface AirlinesRepository extends CrudRepository<Airlines, Long>, JpaRepository<Airlines, Long> {

	Optional< Airlines> findByNameAndWebsite (String name, String website);

	@Modifying
	@Query("delete  from Airlines a where a = :airlines")
	void deleteAirlines (Airlines airlines);
}