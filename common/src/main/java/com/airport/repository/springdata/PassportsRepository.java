package com.airport.repository.springdata;

import com.airport.entity.Passports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PassportsRepository extends CrudRepository<Passports, Long>, JpaRepository<Passports, Long> {

	Optional<Passports> findByNumberAndSeries (Long number, Long series);

	@Modifying
	@Query("delete from Passports p where p.passengerId.id = :passengerId")
	void deletePassportsByPassportsId (Long passengerId);

	@Query("select p from Passports p where p.passengerId.id = :passengerId")
	Optional<List<Passports>> selectPassportsByPassportsId (Long passengerId);
}
