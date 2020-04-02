package com.airline.service;

import com.airline.entity.Passengers;

public interface PassengersService {

	Passengers save (Passengers entity);

	Passengers update (Passengers entity, Long id);

}
