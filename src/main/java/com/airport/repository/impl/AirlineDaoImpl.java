package com.airport.repository.impl;

import com.airport.entity.Airlines;
import com.airport.repository.AirlineDao;
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
	public List<Airlines> findAll () {
		return entityManager.createQuery ("select a from Airlines a", Airlines.class)
		                    .getResultList ();
	}

	@Override
	public Airlines findById (Long id) {
		return entityManager.find (Airlines.class, id);
	}

	@Override
	public void delete (Long id) {
		entityManager.remove (findById (id));
	}

	@Override
	public Airlines save (Airlines entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Airlines.class, entity.getId ());
	}

	@Override
	public Airlines update (Airlines entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Airlines.class, entity.getId ());
	}


	@Override
	public Airlines findByName (String name) {
		TypedQuery<Airlines> query = entityManager.createQuery ("select a from Airlines a where a.name =:name", Airlines.class);
		query.setParameter ("name", name);
		return query.getSingleResult ();
	}
}
