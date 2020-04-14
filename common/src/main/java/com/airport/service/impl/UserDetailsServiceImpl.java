package com.airport.service.impl;

import com.airport.entity.Passengers;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.PassengersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final PassengersRepository passengersRepository;

	@Override
	public UserDetails loadUserByUsername (String login) throws UsernameNotFoundException {
		try {
			Passengers passengers = passengersRepository.findByLogin (login)
			                                            .orElseThrow (() -> new EntityNotFoundException ("login " + login, Passengers.class));

			return new org.springframework.security.core.userdetails.User (passengers.getLogin (), passengers.getPassword (),
					AuthorityUtils.commaSeparatedStringToAuthorityList (passengers.getRole ()
					                                                              .getRole ()
			                                                                      .toString ()));
		} catch (Exception e) {
			throw new UsernameNotFoundException ("Passenger with this login not found");
		}
	}
}
