package com.airline.service.impl;

import com.airline.entity.Passengers;
import com.airline.entity.Passports;
import com.airline.repository.springdata.PassengerRepository;
import com.airline.repository.springdata.PassportsRepository;
import com.airline.service.PassengersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class PassengersServiceImpl implements PassengersService {

	private final PassengerRepository passengerRepository;

	private final PassportsRepository passportsRepository;

	private final EntityManagerFactory entityManager;

	@Override
	@Transactional
	public Passengers save (Passengers passengers) {

		passengers.setCreated (new Timestamp (System.currentTimeMillis ()));

		//passengers.setCountries (countryDao.findByName (entity.getCountry ()));
		//passengers.setTickets (ticketsDao.findByIds (entity.getTickets ()));
		Passengers save = passengerRepository.save(passengers);
		for (Passports p : passengers.getPassports ()){
			p.setPassengersId (save);
			passportsRepository.save(p);
		}

		//passportsRepository.save (passengers.setPassports ());
		passportsRepository.flush ();
		passengerRepository.flush ();
		//passportsRepository.save (set);

		return save;
	}

	@Override
	public Passengers update (Passengers entity, Long id) {

		/*Passengers passengers = converterRequestPassenger.convert (entity, passengersDao.findById (id));
		passengers.setChanged (new Timestamp (System.currentTimeMillis ()));
		passengers.setCountries (countryDao.findByName (entity.getCountry ()));
		Passengers update = passengersDao.update (passengers);

		Set<Passports> set = new HashSet<> ();
		for (PassportRequest p : entity.getPassportRequestSet ()) {
			Passports passports = converterRequestPassport.convertUpdate (p, passportDao.findByTitleAndLongPassengersId (p.getTitle (), update.getId ()));
			set.add (passportDao.save (passports));
		}
		update.setPassports (set);
		return update;*/
		return null;
	}







}

