package com.airport.security.util;

import com.airport.entity.Passenger;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.security.model.JwtPassengerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PassengerAuthService implements UserDetailsService {

	private final PassengersRepository passengersRepository;

	@Override
	public UserDetails loadUserByUsername (String login) {

		Passenger passenger = passengersRepository.findByLogin (login)
		                                          .orElseThrow (() -> new EntityNotFoundException ("login " + login, Passenger.class));

		log.info ("IN loadUserByUsername - user with username: {} successfully loaded", passenger);

		return JwtPassengerFactory.create (passenger);
	}
}
