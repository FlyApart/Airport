package com.airline.repository.impl;

import com.airline.entity.Country;
import com.airline.entity.Flights;
import com.airline.repository.FlightsDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FlightsDaoImpl implements FlightsDao {

	@Autowired
	EntityManager entityManager;



	@Override
	public List<Flights> findAll () {
		return entityManager.createQuery ("select f from Flights f", Flights.class).getResultList ();
	}

	@Override
	public Flights findById (Long id) {
		return entityManager.find (Flights.class,id);
	}

	@Override
	public void delete (Long id) {
		entityManager.joinTransaction ();
		entityManager.remove (findById (id));

	}


	@Override
	public Flights save (Flights entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Flights.class,entity.getId ());
	}

	@Override
	public Flights update (Flights entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Flights.class,entity.getId ());
	}


}
