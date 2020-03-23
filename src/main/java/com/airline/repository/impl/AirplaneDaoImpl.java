package com.airline.repository.impl;

import com.airline.entity.Airplanes;
import com.airline.entity.Country;
import com.airline.entity.Flights;
import com.airline.repository.AirplaneDao;
import com.airline.repository.FlightsDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AirplaneDaoImpl implements AirplaneDao {

	@Autowired
	EntityManager entityManager;

	@Override
	public List<Airplanes> findAll () {
		return entityManager.createQuery ("select f from Airplanes f", Airplanes.class).getResultList ();
	}

	@Override
	public Airplanes findById (Long id) {
		return entityManager.find (Airplanes.class,id);
	}

	@Override
	public void delete (Long id) {
		entityManager.remove (findById (id));
	}

	@Override
	public Airplanes save (Airplanes entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Airplanes.class,entity.getId ());
	}

	@Override
	public Airplanes update (Airplanes entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Airplanes.class,entity.getId ());
	}


	@Override
	public Airplanes findByModel (String model) {
		TypedQuery<Airplanes> query = entityManager.createQuery ("select a from Airplanes a where a.model =:model",Airplanes.class);
		query.setParameter ("model",model);
		return query.getSingleResult ();
	}
}
