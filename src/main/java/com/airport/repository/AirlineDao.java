package com.airport.repository;


import com.airport.entity.Airlines;


public interface AirlineDao extends GenericDao <Airlines, Long> {
	public Airlines findByName (String name);
}
