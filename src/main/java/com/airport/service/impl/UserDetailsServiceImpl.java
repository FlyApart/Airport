package com.airport.service.impl;

import com.airport.entity.Passenger;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.security.model.JwtPassengerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	private final PassengersRepository passengersRepository;

	@Override
	public UserDetails loadUserByUsername (String login) throws UsernameNotFoundException {

		Passenger passenger = passengersRepository.findByLogin (login)
		                                          .orElseThrow (() -> new EntityNotFoundException ("login " + login, Passenger.class));
		log.info("IN loadUserByUsername - user with username: {} successfully loaded", passenger);
		return JwtPassengerFactory.create (passenger);


/*		try {
			Passengers passengers = passengersRepository.findByLogin (login)
			                                            .orElseThrow (() -> new EntityNotFoundException ("login " + login, Passengers.class));

			return new User (passengers.getLogin (),passengers.getPassword (), AuthorityUtils.commaSeparatedStringToAuthorityList (passengers.getRole ()
			                                                                                                                                 .getRole ()
			                                                                                                                                 .toString ()));
			*//*return User.builder ()
			           .password (passengers.getPassword ())
			           .username (passengers.getLogin ())
			           .roles (passengers.getRole ()
			                             .getRole ()
			                             .toString ())
			           .build ();
*//*
		} catch (Exception e) {
			throw new UsernameNotFoundException ("Passenger with this login not found");
		}*/
	}
}
