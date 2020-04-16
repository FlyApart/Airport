package com.airport.repository.hibernate;


import com.airport.entity.Airports;


public interface AirportDao extends GenericDao<Airports, Long> {
	Airports findByTitle (String title);
}
