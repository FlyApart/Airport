package com.airport.repository.springdata;

import com.airport.entity.Passengers;
import com.airport.entity.Passports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

/*@CacheConfig(cacheNames = "passengerInfo")*/
public interface PassengersRepository extends CrudRepository<Passengers, Long>, JpaRepository<Passengers, Long> {

	@Query("select p.passports from Passengers p  where p.id = :id")
	Set<Passports> findPassportsByPassportsId (Long id);

	/*@Cacheable*/
	@Query("from Passengers p where p.login= :login")
	Optional<Passengers> findByLogin (String login);

}