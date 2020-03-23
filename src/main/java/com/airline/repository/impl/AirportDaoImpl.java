package com.airline.repository.impl;

import com.airline.entity.Airplanes;
import com.airline.entity.Airports;
import com.airline.repository.AirplaneDao;
import com.airline.repository.AirportDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AirportDaoImpl implements AirportDao {

	@Autowired
	EntityManager entityManager;

	@Override
	public List<Airports> findAll () {
		return entityManager.createQuery ("select a from Airports a", Airports.class).getResultList ();
	}

	@Override
	public Airports findById (Long id) {
		return entityManager.find (Airports.class,id);
	}

	@Override
	public void delete (Long id) {
		entityManager.remove (findById (id));
	}

	@Override
	public Airports save (Airports entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Airports.class,entity.getId ());
	}

	@Override
	public Airports update (Airports entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Airports.class,entity.getId ());
	}

	@Override
	public Airports findByTitle (String title) {
		TypedQuery<Airports> query = entityManager.createQuery ("select a from Airports a where a.title =:title",Airports.class);
		query.setParameter ("title",title);
		return query.getSingleResult ();
	}
}
