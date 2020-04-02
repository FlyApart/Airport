package com.airline.repository.springdata;


import com.airline.entity.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface CountriesRepository extends CrudRepository<Countries, Long>, JpaRepository<Countries, Long> {
	Countries findByName (String string);
}