package com.airline.repository.springdata;

import com.airline.entity.Passports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PassportsRepository extends CrudRepository<Passports, Long>, JpaRepository<Passports, Long> {
	//List<Passports> findByPassengersId (Long passengersId);
	//Passports findByPassengersId (String title, Long passengersId);
}
