package com.airport.repository.springdata;


import com.airport.entity.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface CountriesRepository extends CrudRepository<Countries, Long>, JpaRepository<Countries, Long> {

	Optional <Countries> findByNameIgnoreCase (String string);

	Optional<List<Countries>> findByPopulationGreaterThan (Long population);
}