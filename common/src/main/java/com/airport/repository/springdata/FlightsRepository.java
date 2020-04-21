package com.airport.repository.springdata;


import com.airport.entity.Flights;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface FlightsRepository extends CrudRepository<Flights, Long>, JpaRepository<Flights, Long> {

	Optional<List<Flights>> findByIdIn (Collection<Long> ids);

	Optional<Flights> findByFightsNumber (String fightsNumber);

	@Query("select f.fightsNumber, depCity.cities.name, arrCity.cities.name, f.departureDate, f.arriveDate, f.price from Flights f  " +
			       "join f.arriveAirport as arrCity " +
			       "join f.departureAirport as depCity " +
			       "where upper(arrCity.cities.name) like upper(:arriveCity)  " +
			       "and upper(depCity.cities.name) like upper(:departureCity)  "+
			       "and f.arriveDate = :arriveDate " +
			       "and f.departureDate = :departureDate ")
	Optional<List<Flights>> findWithAllParam (
			String arriveCity,
			String departureCity,
			LocalDate arriveDate,
			LocalDate departureDate,
			Pageable pageable);

	@Query("select f.fightsNumber, depCity.cities.name, arrCity.cities.name, f.departureDate, f.arriveDate, f.price from Flights f  " +
			       "join f.arriveAirport as arrCity " +
			       "join f.departureAirport as depCity " +
			       "where upper(arrCity.cities.name) like upper(:arriveCity)  " +
			       "and upper(depCity.cities.name) like upper(:departureCity)  "+
			       "and f.departureDate = :departureDate")
	Optional<List<Flights>> findWithNullArriveDateParam (
			String arriveCity,
			String departureCity,
			LocalDate departureDate,
			Pageable pageable);

	@Query("select f.fightsNumber, f.arriveAirport.cities.name, f.departureDate, f.arriveDate, f.price from Flights f  " +
			       "join f.departureAirport as depCity " +
			       "where upper(depCity.cities.name) like upper(:departureCity)  "+
			       "and f.arriveDate = :arriveDate " +
			       "and f.departureDate = :departureDate")
	Optional<List<Flights>> findWithAnyArriveCitiesParam (
			String departureCity,
			LocalDate arriveDate,
			LocalDate departureDate,
			Pageable pageable);

	@Query("select f.fightsNumber, f.arriveAirport.cities.name, f.departureDate, f.arriveDate, f.price from Flights f " +
			       "join f.departureAirport as depCity " +
			       "where upper(depCity.cities.name) like upper(:departureCity)  "+
			       "and f.departureDate = :departureDate")
	Optional<List<Flights>> findWithAnyArriveCitiesAndNullArriveDateParam (
			String departureCity,
			LocalDate departureDate,
			Pageable pageable);

	@Query("select tic.place from Flights f join f.ticket tic where f.id = :id order by tic.place")
	Optional<List<String>> findAllOccupiedSeats (Long id);

}
