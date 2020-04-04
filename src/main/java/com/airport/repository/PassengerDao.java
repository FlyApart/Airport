package com.airport.repository;


import com.airport.entity.Passengers;

public interface PassengerDao extends GenericDao <Passengers, Long> {
	public Passengers findByLogin(String login);
}
