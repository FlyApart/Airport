package com.airline.repository;


import com.airline.entity.Passengers;

public interface PassengersDao extends GenericDao <Passengers, Long> {
	public Passengers findByLogin(String login);
}
