package com.airline.repository.impl;

import com.airline.entity.Passengers;
import com.airline.repository.PassengersDao;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PassengersDaoImpl implements PassengersDao {
	@Autowired
	private EntityManager entityManager;




	@Override
	public List<Passengers> findAll () {
		return entityManager.createQuery ("select p from Passengers p", Passengers.class).getResultList ();
	}

	@Override
	public Passengers findById (Long id) {
			return entityManager.find (Passengers.class, id);
	}

	@Override
	@Transactional
	public void delete (Long id) {
		entityManager.remove (findById (id));
	}

	@Override
	public Passengers save (Passengers entity) {
		entityManager.joinTransaction ();// entityManager.joinTransaction ()
		entityManager.persist(entity);
		return entityManager.find (Passengers.class, entity.getId ());
	}

	@Override
	@Transactional
	public Passengers update (Passengers entity) {
		entityManager.joinTransaction ();
		entityManager.persist(entity);
		return entityManager.find (Passengers.class, entity.getId ());
	}
	@Override
	public Passengers findByLogin(String login) {
			TypedQuery<Passengers> query = entityManager.createQuery("select tu from Passengers tu where tu.login = :login", Passengers.class);
			query.setParameter("login", login);
			return query.getSingleResult();
	}
}
