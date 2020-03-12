package com.airline.repository.impl;

import com.airline.entity.Country;
import com.airline.repository.CountryDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

import java.util.List;

@Repository

public class CountryDaoImpl  implements CountryDao {
	@Autowired
	private SessionFactory sessionFactory;



	@Override
	public List<Country> findAll () {
		try (Session session = sessionFactory.openSession ()) {
			return session.createQuery ("select с from Country с", Country.class)
			              .getResultList ();
		}
	}

	@Override
	public Country findById (Long id) {
		try (Session session = sessionFactory.openSession ()) {
			return session.find (Country.class, id);
		}
	}

	@Override
	public void delete (Long id) {
		try (Session session = sessionFactory.openSession ()) {
			session.remove (findById (id));
		}
	}

	@Override
	public Country save (Country entity) {
		try (Session session = sessionFactory.openSession ()) {
			Transaction transaction = session.getTransaction ();
			transaction.begin ();
			Long newUserID = (Long) session.save (entity);
			transaction.commit ();
			return session.find (Country.class, newUserID);
		}
	}

	@Override
	public Country update (Country entity) {
		try (Session session = sessionFactory.openSession ()) {
			Transaction transaction = session.getTransaction ();
			transaction.begin ();
			session.saveOrUpdate (entity);
			transaction.commit ();
			return session.find (Country.class, entity.getId ());
		}
	}


	@Override
	public Country findByName (String name) {
		try (Session session = sessionFactory.openSession()) {
			TypedQuery<Country> query = session.createQuery("select c from Country c where c.name = :name", Country.class);
			query.setParameter("name", name);
			return query.getSingleResult();
		}
	}
}
