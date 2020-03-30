package com.airline.repository;


import com.airline.entity.Airlines;


public interface AirlineDao extends GenericDao <Airlines, Long> {
	public Airlines findByName (String name);
}
