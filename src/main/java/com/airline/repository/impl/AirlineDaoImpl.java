package com.airline.repository.impl;

import com.airline.entity.Airline;
import com.airline.entity.Airplanes;
import com.airline.repository.AirlineDao;
import com.airline.repository.AirplaneDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AirlineDaoImpl implements AirlineDao {

	@Autowired
	EntityManager entityManager;

	@Override
	public List<Airline> findAll () {
		return entityManager.createQuery ("select f from Airline f", Airline.class).getResultList ();
	}

	@Override
	public Airline findById (Long id) {
		return entityManager.find (Airline.class,id);
	}

	@Override
	public void delete (Long id) {
		entityManager.remove (findById (id));
	}

	@Override
	public Airline save (Airline entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Airline.class,entity.getId ());
	}

	@Override
	public Airline update (Airline entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Airline.class,entity.getId ());
	}


	@Override
	public Airline findByName (String name) {
		TypedQuery<Airline> query = entityManager.createQuery ("select a from Airline a where a.name =:name",Airline.class);
		query.setParameter ("name",name);
		return query.getSingleResult ();
	}
}
