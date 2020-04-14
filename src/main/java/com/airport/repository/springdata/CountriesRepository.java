package com.airport.repository.springdata;


import com.airport.entity.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface CountriesRepository extends CrudRepository<Countries, Long>, JpaRepository<Countries, Long> {

	Optional <Countries> findByName (String string);

	@Modifying
	@Query("delete  from Countries c where c = :countries")
	void deleteCountries (Countries countries);

}