package com.airline.repository.springdata;


import com.airline.entity.Airplanes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface AirplanesRepository extends CrudRepository<Airplanes, Long>, JpaRepository<Airplanes, Long> {
	//public Airplanes findByModel (String model);
}
