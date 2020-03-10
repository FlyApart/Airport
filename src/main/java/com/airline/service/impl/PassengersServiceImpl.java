package com.airline.service.impl;

import com.airline.controller.request.PassengerRequest;
import com.airline.entity.Passengers;
import com.airline.entity.Passports;
import com.airline.entity.vo.PassengersInfo;
import com.airline.repository.CountryDao;
import com.airline.repository.PassengersDao;
import com.airline.repository.PassportDao;
import com.airline.service.PassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PassengersServiceImpl  implements  PassengersService{
	@Autowired
	PassportDao passportDao;
	@Autowired
	PassengersDao passengersDao;
	@Autowired
	CountryDao countryDao;


	public List<PassengersInfo> findAll () {
		/*List<PassengersInfo> list = new ArrayList<> ();
		for (Passengers passengers : passengersDao.findAll()) {
			PassengersInfo info = new PassengersInfo ();
			info.setPassengers(passengers);
			info.setPassports (passportDao.findByPassengersId (passengers.getId ()));
			info.setCountry (countryDao.findById (passengers.getCountry ()));
			list.add (info) ;
		}
		return list;*/
		return null;
	}


	public PassengersInfo findById (Long id) {
		/*PassengersInfo info = new PassengersInfo ();
		info.setPassengers (passengersDao.findById (id));
		info.setPassports (passportDao.findByPassengersId (id));
		info.setCountry (countryDao.findById (info.getPassengers ().getCountry ()));
		return info;*/
		return null;
	}


	public void delete (Long id) {
		passengersDao.delete (id);
	}

	@Override
	public PassengersInfo save (PassengersInfo entity) {
		return null;
	}




	@Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.SERIALIZABLE)
	public Passengers save (PassengerRequest entity)  {
		Passengers passengers = new Passengers();
		passengers.setName (entity.getName ());
		passengers.setSecondName (entity.getSecondName());
		passengers.setBirthDate(entity.getBirthDate());
		passengers.setCreated (new Timestamp (System.currentTimeMillis ()));
		passengers.setCountry (countryDao.findByName (entity.getCountry ()));
		passengers.setLogin(entity.getLogin());
		passengers.setPassword(entity.getPassword());
		Passengers save = passengersDao.save(passengers);

		Passports passports = new Passports ();
		passports.setNumber (entity.getNumber ());
		passports.setSeries (entity.getSeries ());
		passports.setPassengersId (save);

		Set<Passports> set = Collections.singleton (passportDao.save (passports));
		save.setPassports (set);

		return save;
	}


	public PassengersInfo update (PassengersInfo entity) {
		return null;
	}

}

