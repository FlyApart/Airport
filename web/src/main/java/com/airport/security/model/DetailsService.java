package com.airport.security.model;

import com.airport.entity.Passenger;
import com.airport.entity.Role;
import com.airport.entity.RoleName;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.PassengersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DetailsService implements UserDetailsService {

	private final PassengersRepository passengersRepository;

	@Override
	public UserDetails loadUserByUsername (String login) {


	Passenger passenger = passengersRepository.findByLogin (login)
			                                            .orElseThrow (() -> new EntityNotFoundException ("login " + login, Passenger.class));

			return new User (passenger.getLogin (),
					passenger.getPassword (),
					AuthorityUtils.commaSeparatedStringToAuthorityList (passenger.getRole ()
					                                                             .stream ()
					                                                             .map (Role::getRole)
					                                                             .map (RoleName::toString)
					                                                             .collect (Collectors.joining (","))));
	}
}

					/*Passenger passenger = passengersRepository.findByLogin (login)
		                                          .orElseThrow (() -> new EntityNotFoundException ("login " + login, Passenger.class));

		log.info("IN loadUserByUsername - user with username: {} successfully loaded", passenger);

		return JwtPassengerFactory.create (passenger);
*/

		/*				*//*return User.builder ()
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
