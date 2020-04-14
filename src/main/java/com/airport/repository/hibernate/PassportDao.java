package com.airport.repository.hibernate;

import com.airport.entity.Passports;

import java.util.List;

public interface PassportDao extends GenericDao<Passports, Long> {
	List<Passports> findByPassengersId (Long passengersId);

	Passports findByTitleAndLongPassengersId (String title, Long passengersId);
}
