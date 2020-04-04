package com.airport.repository.springdata;


import com.airport.entity.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface FlightsRepository extends CrudRepository<Flights, Long>, JpaRepository<Flights, Long> {}
