package com.airline.service.impl;

import com.airline.entity.Passengers;
import com.airline.entity.Passports;
import com.airline.repository.springdata.CountriesRepository;
import com.airline.repository.springdata.PassengerRepository;
import com.airline.repository.springdata.PassportsRepository;
import com.airline.service.PassengersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PassengersServiceImpl implements PassengersService {

	private final PassengerRepository passengerRepository;
	private final CountriesRepository countriesRepository;

	private final PassportsRepository passportsRepository;

	private final EntityManager entityManager;
	@Override
	@Transactional
	public Passengers save (Passengers passengers) {


		entityManager.joinTransaction ();
		passengers.setCountries (countriesRepository.findByName (passengers.getCountries ().getName ()));
		//passengers.setTickets (ticketsDao.findByIds (entity.getTickets ()));

		Set<Passports> passportsSet = passengers.getPassports ();
		passengers.setPassports (null);
		Passengers save = passengerRepository.saveAndFlush (passengers);

		for (Passports p : passportsSet){
			p.setPassengersId (save);
			passportsRepository.saveAndFlush (p);
		}

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

