package com.airline.service.impl;

import com.airline.entity.Passengers;
import com.airline.entity.Passports;
import com.airline.entity.vo.PassengersInfo;
import com.airline.repository.CountryDao;
import com.airline.repository.PassengersDao;
import com.airline.repository.PassportDao;
import com.airline.service.PassengersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PassengersDetailsImpl  implements PassengersDetailService {
	@Autowired
	PassportDao passportDao;
	@Autowired
	PassengersDao passengersDao;
	@Autowired
	CountryDao countryDao;

	@Override
	public List<PassengersInfo> findAll () {
		List<PassengersInfo> list = new ArrayList<> ();
		for (Passengers passengers : passengersDao.findAll()) {
			PassengersInfo info = new PassengersInfo ();
			info.setPassengers(passengers);
			info.setPassports (passportDao.findByPassengersId (passengers.getId ()));
			info.setCountry (countryDao.findById (passengers.getCountry ()));
			list.add (info) ;
		}
		return list;
	}

	@Override
	public PassengersInfo findById (Long id) {
		PassengersInfo info = new PassengersInfo ();
		info.setPassengers (passengersDao.findById (id));
		info.setPassports (passportDao.findByPassengersId (id));
		info.setCountry (countryDao.findById (info.getPassengers ().getCountry ()));
		return info;
	}

	@Override
	public void delete (Long id) {
		passengersDao.delete (id);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
	public PassengersInfo save (PassengersInfo entity)  {
		Passengers passengers = passengersDao.save (entity.getPassengers ());
		Passports passports = entity.getPassports ().get (0);
		passports.setPassengersId (passengers.getId ());
		passportDao.save (passports);
		return findById (passengers.getId());
	}

	@Override
	public PassengersInfo update (PassengersInfo entity) {
		return null;
	}




}
