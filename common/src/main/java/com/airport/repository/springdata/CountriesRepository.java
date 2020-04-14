package com.airport.repository.springdata;


import com.airport.entity.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface CountriesRepository extends CrudRepository<Countries, Long>, JpaRepository<Countries, Long> {
	Countries findByName (String string);

	@Query("select c from Countries c where c.name = :Name")
	Countries findCountriesByName ( @Param ("Name") String Name);

    @Modifying
    @Query ("delete  from Countries c where c = :countries")
    void deleteCountries (Countries countries);

}