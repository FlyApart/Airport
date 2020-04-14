package com.airport.repository.hibernate;


import com.airport.entity.Airports;


public interface AirportDao extends GenericDao<Airports, Long> {
	public Airports findByTitle (String title);
}
