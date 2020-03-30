package com.airline.repository;


import com.airline.entity.Countries;


public interface CountryDao extends GenericDao <Countries, Long> {
	public Countries findByName(String name);
}
