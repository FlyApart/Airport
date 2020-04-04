package com.airline.repository.springdata;

import com.airline.entity.Passports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PassportsRepository extends CrudRepository <Passports, Long>, JpaRepository<Passports, Long> {
	Set<Passports> findByPassengersId (Long passengersId);
	//Passports findByPassengersId (String title, Long passengersId);
}
