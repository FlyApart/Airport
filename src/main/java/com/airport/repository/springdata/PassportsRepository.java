package com.airport.repository.springdata;

import com.airport.entity.Passengers;
import com.airport.entity.Passports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface PassportsRepository extends CrudRepository<Passports, Long>, JpaRepository<Passports, Long> {
	Optional<Set<Passports>> findByPassengersId (Passengers passengersId);
	//Set<Passports> findByPassengers (Passengers passengers);
	//Passports findByPassengersId (String title, Long passengersId);

	@Query("select p from Passports p where p.passengersId = :idP")
	Optional<Set<Passports>> qweqweqweqw (@Param("idP") Long idP);

	@Modifying
	@Query("delete  from Passports p where p = :passport")
	void deletePassports (@Param("passport") Passports passport);

	@Modifying
	@Query("delete  from Passports p where p in (:passport)")
	void deletePassports (@Param("passport") Set<Passports> passports);

}
