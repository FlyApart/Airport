package com.airline.repository.impl;

import com.airline.entity.Passengers;
import com.airline.entity.Passports;
import com.airline.repository.PassengersDao;
import com.airline.repository.PassportDao;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository

public class PassportDaoImpl implements PassportDao {
	@Autowired
	private SessionFactory sessionFactory;



	@Override
	public List<Passports> findAll () {
		try (Session session = sessionFactory.openSession ()) {
			return session.createQuery ("select p from Passports p", Passports.class)
			              .getResultList ();
		}
	}

	@Override
	public Passports findById (Long id) {
		try (Session session = sessionFactory.openSession ()) {
			return session.find (Passports.class, id);
		}
	}

	@Override
	public void delete (Long id) {
		try (Session session = sessionFactory.openSession ()) {
			session.remove (findById (id));
		}
	}

	@Override
	public Passports save (Passports entity) {
		try (Session session = sessionFactory.openSession ()) {
			Transaction transaction = session.getTransaction ();
			transaction.begin ();
			Long newUserID = (Long) session.save (entity);
			transaction.commit ();
			return session.find (Passports.class, newUserID);
		}
	}

	@Override
	public Passports update (Passports entity) {
		try (Session session = sessionFactory.openSession ()) {
			Transaction transaction = session.getTransaction ();
			transaction.begin ();
			session.saveOrUpdate (entity);
			transaction.commit ();
			return session.find (Passports.class, entity.getId ());
		}
	}


	@Override
	public List<Passports> findByPassengersId (Long passengersId) {
		try (Session session = sessionFactory.openSession ()) {
			TypedQuery<Passports> query = session.createQuery ("select p from Passports p where p.passengersId =:passengersId", Passports.class);
			query.setParameter("passengersId", passengersId);
			return query.getResultList ();
		}

		}
}
