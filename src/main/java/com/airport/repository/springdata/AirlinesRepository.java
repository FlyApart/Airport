package com.airport.repository.springdata;


import com.airport.entity.Airlines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface AirlinesRepository extends CrudRepository<Airlines, Long>, JpaRepository<Airlines, Long>{
	Airlines findByName (String name);

	@Modifying
	@Query("delete  from Airlines a where a = :airlines")
	void deleteCities(Airlines airlines);
}