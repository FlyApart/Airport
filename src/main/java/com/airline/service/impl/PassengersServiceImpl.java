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
import com.airline.util.converters.ConverterRequestPassport;
import com.airline.util.converters.ConverterRequestPassenger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class PassengersServiceImpl implements PassengersService {

	private final PassportDao passportDao;

	private final PassengerDao passengersDao;

	private final CountryDao countryDao;

	private final TicketsDao ticketsDao;

	private final ConverterRequestPassenger converterRequestPassenger;

	private final ConverterRequestPassport converterRequestPassport;

	@Override
	public Passengers save (PassengerSaveRequest entity) {

		Passengers passengers = converterRequestPassenger.convert (entity);
		passengers.setCreated (new Timestamp (System.currentTimeMillis ()));
		passengers.setCountries (countryDao.findByName (entity.getCountry ()));
		passengers.setTickets (ticketsDao.findByIds (entity.getTickets ()));
		Passengers save = passengersDao.save (passengers);

		Set<Passports> set = new HashSet<> ();
		for (PassportRequest p : entity.getPassportRequestSet ()) {
			Passports passports = converterRequestPassport.convert (p);
			passports.setPassengersId (save);
			//Passports pass = passportDao.save (passports);
			set.add (passportDao.save (passports));
		}
		save.setPassports (set);

		return save;
	}

	@Override
	public Passengers update (PassengerUpdateRequest entity, Long id) {

		Passengers passengers = converterRequestPassenger.convert (entity, passengersDao.findById (id));
		passengers.setChanged (new Timestamp (System.currentTimeMillis ()));
		passengers.setCountries (countryDao.findByName (entity.getCountry ()));
		Passengers update = passengersDao.update (passengers);

		Set<Passports> set = new HashSet<> ();
		for (PassportRequest p : entity.getPassportRequestSet ()) {
			Passports passports = converterRequestPassport.convertUpdate (p, passportDao.findByTitleAndLongPassengersId (p.getTitle (), update.getId ()));
			set.add (passportDao.save (passports));
		}
		update.setPassports (set);
		return update;
	}


}

