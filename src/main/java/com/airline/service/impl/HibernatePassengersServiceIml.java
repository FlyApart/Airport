package com.airline.service.impl;

import com.airline.entity.Passengers;
import com.airline.repository.PassengersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernatePassengersServiceIml {
	@Autowired
	private PassengersDao passengersDao;

	public List<Passengers> findAll() {

		return passengersDao.findAll();
	}
}
