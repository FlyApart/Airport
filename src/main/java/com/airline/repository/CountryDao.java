package com.airline.repository;


import com.airline.entity.Countries;


public interface CountryDao extends /*CrudRepository<Countries, Long>*/ GenericDao<Countries, Long> {
	public Countries findByName(String name);
}
