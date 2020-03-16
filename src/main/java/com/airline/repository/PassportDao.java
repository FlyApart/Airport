package com.airline.repository;

import com.airline.entity.Passports;

import java.util.List;

public interface PassportDao extends GenericDao<Passports, Long> {
	List<Passports> findByPassengersId(Long passengersId);
	Passports findByTitle (String title);
}
