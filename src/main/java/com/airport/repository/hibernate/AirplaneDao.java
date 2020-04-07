package com.airport.repository.hibernate;


import com.airport.entity.Airplanes;


public interface AirplaneDao extends GenericDao <Airplanes, Long> {
	public Airplanes findByModel (String model);
}
