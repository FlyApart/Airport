package com.airline.repository;


import com.airline.entity.Passengers;

public interface PassengerDao extends GenericDao <Passengers, Long> {
	public Passengers findByLogin(String login);
}
