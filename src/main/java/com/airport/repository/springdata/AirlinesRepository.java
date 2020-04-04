package com.airport.repository.springdata;


import com.airport.entity.Airlines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface AirlinesRepository extends CrudRepository<Airlines, Long>, JpaRepository<Airlines, Long>{
	//public Airlines findByName (String name);
}