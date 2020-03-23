package com.airline.repository.impl;

import com.airline.entity.Country;
import com.airline.repository.CountryDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import java.util.List;

@Repository
public class CountryDaoImpl  implements CountryDao {

	@Autowired
	private EntityManager entityManager;



	@Override
	public List<Country> findAll () {
			return entityManager.createQuery ("select с from Country с", Country.class).getResultList ();
	}

	@Override
	public Country findById (Long id) {

			return entityManager.find (Country.class, id);

	}

	@Override
	public void delete (Long id) {
			entityManager.remove (findById (id));
	}

	@Override
	public Country save (Country entity) {
		//EntityTransaction entityTransaction = entityManager.getTransaction ();
			//entityTransaction.begin ();
		entityManager.joinTransaction ();
			entityManager.persist (entity);
			//entityTransaction.commit ();
			return  entityManager.find (Country.class, entity.getId ());
	}

	@Override
	public Country update (Country entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return  entityManager.find (Country.class, entity.getId ());
	}


	@Override
	public Country findByName (String name) {
			TypedQuery<Country> query = entityManager.createQuery("select c from Country c where c.name = :name", Country.class);
			query.setParameter("name", name);
			return query.getSingleResult();
	}
}
