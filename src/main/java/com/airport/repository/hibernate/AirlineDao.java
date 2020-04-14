package com.airport.repository.hibernate;


import com.airport.entity.Airlines;


public interface AirlineDao extends GenericDao<Airlines, Long> {
	public Airlines findByName (String name);
}
