package com.airline.repository;


import com.airline.entity.Airplanes;
import com.airline.entity.Flights;


public interface AirplaneDao extends GenericDao <Airplanes, Long> {
	public Airplanes findByModel (String model);
}
