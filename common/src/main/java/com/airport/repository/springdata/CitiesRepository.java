package com.airport.repository.springdata;


import com.airport.entity.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface CitiesRepository extends CrudRepository<Cities, Long>, JpaRepository<Cities, Long> {

	Optional<Cities> findByNameIgnoreCase (String string);


}