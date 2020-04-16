package com.airport.repository.hibernate;


import com.airport.entity.Countries;


public interface CountryDao extends /*CrudRepository<Countries, Long>*/ GenericDao<Countries, Long> {
	Countries findByName (String name);
}
