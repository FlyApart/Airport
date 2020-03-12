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
public class PassengersServiceImpl implements PassengersService{
	@Autowired
	PassportDao passportDao;
	@Autowired
	PassengersDao passengersDao;
	@Autowired
	CountryDao countryDao;

	@Override
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

	@Override
	public Passengers update (PassengerRequest entity, Long id) {
		Passengers passengers = passengersDao.findById (id);
		passengers.setName(entity.getName());
		passengers.setSecondName (entity.getSecondName());
		passengers.setBirthDate(entity.getBirthDate());
		passengers.setChanged (new Timestamp (System.currentTimeMillis ()));
		passengers.setCountry (countryDao.findByName (entity.getCountry ()));
		passengers.setLogin(entity.getLogin());
		passengers.setPassword(entity.getPassword());
		Passengers update = passengersDao.update(passengers);

		/*List<Passports> passports = passportDao.findByPassengersId (id);
		passports.setNumber (entity.getNumber ());
		passports.setSeries (entity.getSeries ());
		passports.setPassengersId (save);*/

		/*Set<Passports> set = Collections.singleton (passportDao.save (passports));
		save.setPassports (set);
*/
		return update;
	}


}

