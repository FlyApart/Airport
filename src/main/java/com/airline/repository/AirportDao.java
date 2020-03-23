package com.airline.repository;


import com.airline.entity.Airplanes;
import com.airline.entity.Airports;


public interface AirportDao extends GenericDao <Airports, Long> {
	public Airports findByTitle (String title);
}
