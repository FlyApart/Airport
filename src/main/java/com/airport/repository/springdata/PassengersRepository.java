package com.airport.repository.springdata;

import com.airport.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/*@CacheConfig(cacheNames = "passengerInfo")*/
public interface PassengersRepository extends CrudRepository<Passenger, Long>, JpaRepository<Passenger, Long> {

	/*@Cacheable*/
	@Query("from Passenger p where p.login= :login")
	Optional<Passenger> findByLogin (String login);

}