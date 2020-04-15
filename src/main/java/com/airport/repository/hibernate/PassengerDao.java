package com.airport.repository.hibernate;


import com.airport.entity.Passenger;

public interface PassengerDao extends GenericDao<Passenger, Long> {
	public Passenger findByLogin (String login);
}
