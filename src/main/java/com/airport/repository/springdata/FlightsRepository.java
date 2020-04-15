package com.airport.repository.springdata;


import com.airport.entity.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface FlightsRepository extends CrudRepository<Flights, Long>, JpaRepository<Flights, Long> {

	@Modifying
	@Query("delete  from Flights f where f = :flights")
	void deleteFlights (Flights flights);

	@Query("select f from Flights f where f.id in :ids")//TODO
	Optional<List<Flights>> findByIds (List<Long> ids);

	Optional<Flights> findByFightsNumber (String fightsNumber);

}
