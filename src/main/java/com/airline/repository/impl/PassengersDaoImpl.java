package com.airline.repository.impl;

import com.airline.entity.Passengers;
import com.airline.repository.PassengersDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Qualifier("passengersDaoImpl")
public class PassengersDaoImpl implements PassengersDao {
	@Autowired
	private SessionFactory sessionFactory;



	@Override
	public List<Passengers> findAll () {
		try (Session session = sessionFactory.openSession ()) {
			return session.createQuery ("select p from Passengers p", Passengers.class)
			              .getResultList ();
		}
	}

	@Override
	public Passengers findById (Long id) {
		try (Session session = sessionFactory.openSession ()) {
			Passengers p = session.find (Passengers.class, id);
			return session.find (Passengers.class, id);
		}
	}



	@Override
	@Transactional
	public void delete (Long id) {
		try (Session session = sessionFactory.openSession ()) {
			//session.delete ("Passengers",findById(id));
//			TypedQuery<Passengers> query = session.createQuery("delete from Passengers p where p.id = :id", Passengers.class);
//			query.setParameter("id", findById (id));
			Passengers passengers = session.load (Passengers.class,id);
			session.delete(passengers);
			session.flush ();
		}
	}

	@Override
	public Passengers save (Passengers entity) {
		try (Session session = sessionFactory.openSession ()) {
			Transaction transaction = session.getTransaction ();
			transaction.begin ();
			Long newUserID = (Long) session.save (entity);
			transaction.commit ();
			return session.find (Passengers.class, newUserID);
		}
	}

	@Override
	public Passengers update (Passengers entity) {
		try (Session session = sessionFactory.openSession ()) {
			Transaction transaction = session.getTransaction ();
			transaction.begin ();
			session.saveOrUpdate (entity);
			transaction.commit ();
			return session.find (Passengers.class, entity.getId ());
		}
	}
	@Override
	public Passengers findByLogin(String login) {
		try (Session session = sessionFactory.openSession()) {
			//SQLQuery
			//            NativeQuery<TestUser> nativeQuery = session.createNativeQuery("select * from test_user", TestUser.class);
			//            nativeQuery.getSingleResult();
			//            Query query = session.createQuery("" +
			//                    "select tu from TestUser tu where tu.userName = :login", TestUser.class);
			//            query.setParameter("login", login);
			//            return (TestUser)query.getSingleResult();
			TypedQuery<Passengers> query = session.createQuery("select tu from Passengers tu where tu.login = :login", Passengers.class);
			query.setParameter("login", login);
			return query.getSingleResult();
		}
	}
}
