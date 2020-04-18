package com.airport.repository.springdata;


import com.airport.entity.Airline;
import com.airport.entity.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface FlightsRepository extends CrudRepository<Flights, Long>, JpaRepository<Flights, Long> {

	@Query("select f from Flights f where f.id in :ids")
	Optional<List<Flights>> findByIds (List<Long> ids);

	Optional<Flights> findByFightsNumber (String fightsNumber);

	@Query("select f from Flights f join fetch f.airline as id")// todo delete
	Optional<List<Flights>> findByIdsJoinA (List<Airline> id);

}
