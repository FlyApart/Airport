package com.airport.repository.hibernate;


import com.airport.entity.Passenger;

public interface PassengerDao extends GenericDao<Passenger, Long> {
	Passenger findByLogin (String login);
}
