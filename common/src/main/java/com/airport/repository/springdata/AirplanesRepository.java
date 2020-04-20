package com.airport.repository.springdata;


import com.airport.entity.Airplanes;
import com.airport.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface AirplanesRepository extends CrudRepository<Airplanes, Long>, JpaRepository<Airplanes, Long> {
	Optional<Airplanes> findByModelIgnoreCase (String model);

	@Query("select a.id, a.seats, a.ComfortSeats, a.businessSeats from Airplanes a where a.id in (:id) group by a.id")
	Optional<List<Airplanes>> findSummarySeatById (Long[] id);

	Optional<List<Airplanes>> findByBuiltAfterAndCountries_NameAndStatus (Date built, String countriesName, Status status);

}

