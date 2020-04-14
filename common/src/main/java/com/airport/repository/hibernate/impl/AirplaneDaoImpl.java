package com.airport.repository.hibernate.impl;

import com.airport.entity.Airplanes;
import com.airport.repository.hibernate.AirplaneDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AirplaneDaoImpl implements AirplaneDao {

	private final EntityManager entityManager;

	@Override
	public List<Airplanes> findAll () {
		return entityManager.createQuery ("select a from Airplanes a", Airplanes.class)
		                    .getResultList ();
	}

	@Override
	public Airplanes findById (Long id) {
		return entityManager.find (Airplanes.class, id);
	}

	@Override
	public void delete (Long id) {
		entityManager.remove (findById (id));
	}

	@Override
	public Airplanes save (Airplanes entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Airplanes.class, entity.getId ());
	}

	@Override
	public Airplanes update (Airplanes entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Airplanes.class, entity.getId ());
	}


	@Override
	public Airplanes findByModel (String model) {
		TypedQuery<Airplanes> query = entityManager.createQuery ("select a from Airplanes a where a.model =:model", Airplanes.class);
		query.setParameter ("model", model);
		return query.getSingleResult ();
	}
}
