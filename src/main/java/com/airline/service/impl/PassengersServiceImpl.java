package com.airline.service.impl;

import com.airline.controller.request.PassengerSaveRequest;
import com.airline.controller.request.PassengerUpdateRequest;
import com.airline.controller.request.PassportRequest;
import com.airline.entity.Passengers;
import com.airline.entity.Passports;
import com.airline.repository.CountryDao;
import com.airline.repository.PassengerDao;
import com.airline.repository.PassportDao;
import com.airline.repository.TicketsDao;
import com.airline.service.PassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class PassengersServiceImpl implements PassengersService{
	@Autowired
	PassportDao passportDao;
	@Autowired
	PassengerDao passengersDao;
	@Autowired
	CountryDao countryDao;
	@Autowired
	EntityManager entityManager;
	@Autowired
	TicketsDao ticketsDao;

	@Override// fix transactional
	public Passengers save (PassengerSaveRequest entity)  {

			Passengers passengers = new Passengers();
			passengers.setName (entity.getName ());
			passengers.setSecondName (entity.getSecondName());
			passengers.setBirthDate(entity.getBirthDate());
			passengers.setCreated (new Timestamp (System.currentTimeMillis ()));
			passengers.setCountry (countryDao.findByName (entity.getCountry ()));
			passengers.setLogin(entity.getLogin());
			passengers.setPassword(entity.getPassword());

		    passengers.setTickets (ticketsDao.findByIds (entity.getTickets ()));

			Passengers save = passengersDao.save(passengers);

			Set<Passports> set = new HashSet<> ();
			for (PassportRequest p : entity.getPassportRequestSet ()) {
				Passports passports = new Passports ();
				passports.setPassengersId (save);
				passports.setTitle (p.getTitle ());
				passports.setNumber (p.getNumber ());
				passports.setSeries (p.getSeries ());
				Passports pass = passportDao.save (passports);
				set.add (pass);
			}
			save.setPassports (set);

			return save;
	}

	@Override
	public Passengers update (PassengerUpdateRequest entity, Long id) {
		Passengers passengers = passengersDao.findById (id);
		passengers.setName(entity.getName());
		passengers.setSecondName (entity.getSecondName());
		passengers.setBirthDate(entity.getBirthDate());
		passengers.setChanged (new Timestamp (System.currentTimeMillis ()));
		passengers.setCountry (countryDao.findByName (entity.getCountry ()));
		passengers.setPassword(entity.getPassword());

		Passengers update = passengersDao.update(passengers);


		Set<Passports> set = new HashSet<> ();
		for (PassportRequest p : entity.getPassportRequestSet ()) {
			Passports passports = passportDao.findByTitleAndLongPassengersId (p.getTitle (),update.getId ());
			passports.setNumber (p.getNumber ());
			passports.setSeries (p.getSeries ());
			Passports pass = passportDao.save (passports);
			set.add (pass);
		}
		update.setPassports (set);
		return update;
	}


}

