package com.airport.repository.hibernate;


import com.airport.entity.Airplanes;


public interface AirplaneDao extends GenericDao<Airplanes, Long> {
	Airplanes findByModel (String model);
}
