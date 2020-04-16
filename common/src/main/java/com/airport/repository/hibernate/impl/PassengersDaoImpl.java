package com.airport.repository.hibernate.impl;

import com.airport.entity.Passenger;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.hibernate.PassengerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PassengersDaoImpl implements PassengerDao {

	private final EntityManager entityManager;

	@Override
	public List<Passenger> findAll () {
		return entityManager.createQuery ("select p from Passenger p", Passenger.class)
		                    .getResultList ();
	}

	@Override
	// TODO get optional in all entities
	public Passenger findById (Long id) {
		//return entityManager.find (Passengers.class, id);
		Optional<Passenger> passengersOptional = Optional.ofNullable (entityManager.find (Passenger.class, id));
		return passengersOptional.orElseThrow (EntityNotFoundException::new);
	}

	@Override
	public void delete (Long id) {
		entityManager.remove (findById (id));
	}

	@Override
	public Passenger save (Passenger entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Passenger.class, entity.getId ());
	}

	@Override
	public Passenger update (Passenger entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Passenger.class, entity.getId ());
	}

	@Override
	public Passenger findByLogin (String login) {
		TypedQuery<Passenger> query = entityManager.createQuery ("select p from Passenger p where p.login = :login", Passenger.class);
		query.setParameter ("login", login);
		return query.getSingleResult ();
	}
}
