package com.airport.repository.springdata;


import com.airport.entity.Cities;
import com.airport.entity.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface CitiesRepository extends CrudRepository<Cities, Long>, JpaRepository<Cities, Long> {
    Cities findByName(String string);

	@Query("select c from Cities c where c.name = :Name")
    Cities findCitiesByName(@Param("Name") String Name);

    @Modifying
    @Query ("delete  from Cities c where c = :countries")
    void deleteCities(Cities countries);

}