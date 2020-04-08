package com.airport.service.impl;

import com.airport.entity.Passengers;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.repository.springdata.PassportsRepository;
import com.airport.service.PassengersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class PassengersServiceImpl implements PassengersService {

	private final PassengersRepository passengersRepository;
	private final PassportsRepository passportsRepository;

	private final EntityManager entityManager;

	@Override
    @Transactional
	public Passengers save (Passengers passenger) {

		//passengers.setTickets (ticketsDao.findByIds (entity.getTickets ()));

		/*Set<Passports> passportsSet = passenger.getPassports ();
		passenger.setPassports (null);
		Passengers save = passengerRepository.save (passenger);
		for (Passports p : passportsSet){
			p.setPassengersId (save);
			passportsSet.add (passportsRepository.save (p));
		}
		passengerRepository.flush();
		passportsRepository.flush();

		save.setPassports (passportsSet);
		return save;*/

Passengers p = passengersRepository.saveAndFlushPassengers(passenger);
passengersRepository.flush();
        return p;
	}

	@Override
	@Transactional
	public Passengers update (Passengers passenger) {
		entityManager.joinTransaction ();

		passenger.setChanged (new Timestamp (System.currentTimeMillis ()));
		Passengers update = passengersRepository.saveAndFlush (passenger);

		/*for (Passports p : passportsSet){
			p.setPassengersId (save);
			passportsSet.add (passportsRepository.saveAndFlush (p));
		}
		save.setPassports (passportsSet);*/
		return update;

	}







}

