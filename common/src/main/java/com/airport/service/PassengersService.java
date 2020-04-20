package com.airport.service;

import com.airport.entity.Passenger;

public interface PassengersService {

	Passenger saveAndUpdate (Passenger passenger);

	String activate (String code);

}

