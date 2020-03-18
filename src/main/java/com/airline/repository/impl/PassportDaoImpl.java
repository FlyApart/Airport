package com.airline.repository.impl;

import com.airline.entity.Passports;
import com.airline.repository.PassportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PassportDaoImpl implements PassportDao {

	@Autowired
	private EntityManager entityManager;



	@Override
	public List<Passports> findAll () {
		return entityManager.createQuery("select tu from Passports tu", Passports.class).getResultList();
	}

	@Override
	public Passports findById (Long id) {
		return entityManager.find(Passports.class,id);
	}

	@Override
	@Transactional
	public void delete (Long id) {
		entityManager.remove (findById (id));
	}

	@Override
	@Transactional
	public Passports save (Passports entity) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entity);
		transaction.commit();
		return entityManager.find(Passports.class, entity.getId ());
	}

	@Override
	public Passports update (Passports entity) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entity);
		transaction.commit();
		return entityManager.find(Passports.class, entity.getId ());
	}


	@Override
	public List<Passports> findByPassengersId (Long passengersId) {
		TypedQuery<Passports> query = entityManager.createQuery ("select p from Passports p where p.passengersId.id =:passengersId", Passports.class);
		query.setParameter("passengersId", passengersId);
		return query.getResultList ();
	}

	@Override
	public Passports findByTitle (String title) {
			TypedQuery<Passports> query = entityManager.createQuery ("select p from Passports p where p.title =:title", Passports.class);
			query.setParameter("title", title);
			return query.getSingleResult ();
	}
}
