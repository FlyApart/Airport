package com.airline.repository.impl;

import com.airline.entity.Countries;
import com.airline.repository.CountryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CountryDaoImpl implements CountryDao {

	private final EntityManager entityManager;

	@Override
	public List<Countries> findAll () {
		return entityManager.createQuery ("select с from Countries с", Countries.class)
		                    .getResultList ();
	}

	@Override
	public Countries findById (Long id) {
		return entityManager.find (Countries.class, id);
	}

	@Override
	public void delete (Long id) {
		entityManager.remove (findById (id));
	}

	@Override
	public Countries save (Countries entity) {

		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Countries.class, entity.getId ());
	}

	@Override
	public Countries update (Countries entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Countries.class, entity.getId ());
	}


	@Override
	public Countries findByName (String name) {
		TypedQuery<Countries> query = entityManager.createQuery ("select c from Countries c where c.name = :name", Countries.class);
		query.setParameter ("name", name);
		return query.getSingleResult ();
	}
}
