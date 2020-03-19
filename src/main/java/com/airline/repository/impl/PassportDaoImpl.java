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
@Transactional
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
	public Passports save (Passports entity) {
		entityManager.joinTransaction ();
		entityManager.persist(entity);
		return entityManager.find(Passports.class, entity.getId ());
	}

	@Override
	public Passports update (Passports entity) {
		entityManager.joinTransaction ();
		entityManager.persist(entity);
		return entityManager.find(Passports.class, entity.getId ());
	}


	@Override
	public List<Passports> findByPassengersId (Long passengersId) {
		TypedQuery<Passports> query = entityManager.createQuery ("select p from Passports p where p.passengersId.id =:passengersId", Passports.class);
		query.setParameter("passengersId", passengersId);
		return query.getResultList ();
	}

	@Override
	public Passports findByTitleAndLongPassengersId  (String title, Long passengersId) {
			TypedQuery<Passports> query = entityManager.createQuery ("select p from Passports p where p.title =:title and p.passengersId.id =:passengersId", Passports.class);
			query.setParameter("title", title);
			query.setParameter("passengersId", passengersId);
			return query.getSingleResult ();
	}
}
