package com.airport.controller.converters.passengers;

import com.airport.controller.converters.EntityConverter;
import com.airport.controller.request.change.PassengerUpdateRequest;
import com.airport.controller.request.create.PassengerSaveRequest;
import com.airport.entity.Cities;
import com.airport.entity.Passenger;
import com.airport.entity.Passports;
import com.airport.entity.Role;
import com.airport.entity.RoleName;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityAlreadyExistException;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.CitiesRepository;
import com.airport.repository.springdata.PassengersRepository;
import com.airport.repository.springdata.RoleRepository;
import com.airport.util.ProjectDate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public abstract class ConverterRequestPassengers<S, T> extends EntityConverter<S, T> {

	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;
	private final CitiesRepository citiesRepository;
	private final PassengersRepository passengersRepository;

	protected Passenger doConvert (Passenger passenger, PassengerSaveRequest entity) {
		passenger.setName (entity.getName ());
		passenger.setSecondName (entity.getSecondName ());
		passenger.setPassword (passwordEncoder.encode (entity.getPassword ()));
		passenger.setBirthDate (entity.getBirthDate ());
		passenger.setCreated (new ProjectDate ().getCurrentTime ());
		passenger.setLogin (entity.getLogin ());
		return passenger;
	}

	protected Passenger doConvert (Passenger passenger, PassengerUpdateRequest entity) {
		if (entity.getId () != null) {
			passenger.setId (Long.valueOf (entity.getId ()));
		}

		if (entity.getName () != null) {
			passenger.setName (entity.getName ());
		}

		if (entity.getSecondName () != null) {
			passenger.setSecondName (entity.getSecondName ());
		}

		if (entity.getPassword () != null) {
			passenger.setPassword (passwordEncoder.encode (entity.getPassword ()));
		}

		if (entity.getBirthDate () != null) {
			passenger.setBirthDate (entity.getBirthDate ());
		}

		passenger.setChanged (new ProjectDate ().getCurrentTime ());

		return passenger;
	}

	protected Cities findCity (Class<?> sClass, String name) {
		return citiesRepository.findByNameIgnoreCase (name)
		                       .orElseThrow (()-> new ConversionException (sClass, Passenger.class, name, new EntityNotFoundException ("City with name = " + name, Cities.class)));
	}

	protected void isUniqueLogin (Class<?> sClass, String login) {

		boolean unique = passengersRepository.findByLogin (login)
		                                     .isPresent ();
		if (unique) {
			throw new ConversionException (sClass, Passports.class, login, new EntityAlreadyExistException ("Passengers with login = " + login));
		}
	}

	protected Set<Role> getRole (){
		Set<Role> role= new HashSet<> ();
		role.add (roleRepository.findByRoleName (RoleName.USER)
		                        .orElseThrow (() -> new EntityNotFoundException ("Role name = "+RoleName.USER,Role.class)));
		return role;
	}

	protected Passenger findPassengerById (Class<?> sClass, Long id) {

		return passengersRepository.findById (id)
		                           .orElseThrow (()-> new ConversionException (sClass, Passenger.class, id, new EntityNotFoundException (Passenger.class,id)));
	}
}
