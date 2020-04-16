package com.airport.repository.hibernate.impl;

import com.airport.entity.Airline;
import com.airport.repository.hibernate.AirlineDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AirlineDaoImpl implements AirlineDao {

	private final EntityManager entityManager;

	@Override
	public List<Airline> findAll () {
		return entityManager.createQuery ("select a from Airline a", Airline.class)
		                    .getResultList ();
	}

	@Override
	public Airline findById (Long id) {
		return entityManager.find (Airline.class, id);
	}

	@Override
	public void delete (Long id) {
		entityManager.remove (findById (id));
	}

	@Override
	public Airline save (Airline entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Airline.class, entity.getId ());
	}

	@Override
	public Airline update (Airline entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Airline.class, entity.getId ());
	}


	@Override
	public Airline findByName (String name) {
		TypedQuery<Airline> query = entityManager.createQuery ("select a from Airline a where a.name =:name", Airline.class);
		query.setParameter ("name", name);
		return query.getSingleResult ();
	}
}
