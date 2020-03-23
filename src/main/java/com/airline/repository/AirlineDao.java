package com.airline.repository;


import com.airline.entity.Airline;
import com.airline.entity.Airplanes;


public interface AirlineDao extends GenericDao <Airline, Long> {
	public Airline findByName (String name);
}
