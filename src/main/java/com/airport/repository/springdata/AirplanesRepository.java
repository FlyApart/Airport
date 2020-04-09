package com.airport.repository.springdata;


import com.airport.entity.Airlines;
import com.airport.entity.Airplanes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface AirplanesRepository extends CrudRepository<Airplanes, Long>, JpaRepository<Airplanes, Long> {
	//public Airplanes findByModel (String model);

    @Modifying
    @Query("delete  from Airplanes a where a = :airplane")
    void deleteCities(Airplanes airplane);

}
