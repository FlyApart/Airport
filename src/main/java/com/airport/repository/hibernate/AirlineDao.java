package com.airport.repository.hibernate;


import com.airport.entity.Airline;


public interface AirlineDao extends GenericDao<Airline, Long> {
	public Airline findByName (String name);
}
