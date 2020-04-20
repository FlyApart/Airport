package com.airport.repository.springdata;

import com.airport.entity.Passports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PassportsRepository extends CrudRepository<Passports, Long>, JpaRepository<Passports, Long> {

	Optional<Passports> findByNumberAndSeries (Long number, Long series);

	Optional<List<Passports>> findByPassengerId_Id (Long passengerId);

	long deleteByPassengerId_Id (Long id);

}

