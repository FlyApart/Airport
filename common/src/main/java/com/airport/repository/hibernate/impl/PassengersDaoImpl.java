package com.airport.repository.hibernate.impl;

import com.airport.entity.Passengers;
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
	public List<Passengers> findAll () {
		return entityManager.createQuery ("select p from Passengers p", Passengers.class)
		                    .getResultList ();
	}

	@Override
    // TODO get optional in all entities
	public Passengers findById (Long id) {
		//return entityManager.find (Passengers.class, id);
        Optional <Passengers> passengersOptional = Optional.ofNullable(entityManager.find(Passengers.class, id));
        return passengersOptional.orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public void delete (Long id) {
		entityManager.remove (findById (id));
	}

	@Override
	public Passengers save (Passengers entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Passengers.class, entity.getId ());
	}

	@Override
	public Passengers update (Passengers entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Passengers.class, entity.getId ());
	}

	@Override
	public Passengers findByLogin (String login) {
		TypedQuery<Passengers> query = entityManager.createQuery ("select p from Passengers p where p.login = :login", Passengers.class);
		query.setParameter ("login", login);
		return query.getSingleResult ();
	}
}
