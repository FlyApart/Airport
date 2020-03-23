package com.airline.repository.impl;

import com.airline.entity.Tickets;
import com.airline.repository.TicketsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Repository
public class TicketsDaoImpl implements TicketsDao {

	@Autowired
	private EntityManager entityManager;



	@Override
	public List<Tickets> findAll () {
			return entityManager.createQuery ("select с from Tickets с", Tickets.class).getResultList ();
	}

	@Override
	public Tickets findById (Long id) {

			return entityManager.find (Tickets.class, id);

	}

	@Override
	public void delete (Long id) {
			entityManager.remove (findById (id));
	}

	@Override
	public Tickets save (Tickets entity) {
		entityManager.joinTransaction ();
			entityManager.persist (entity);
			return  entityManager.find (Tickets.class, entity.getId ());
	}

	@Override
	public Tickets update (Tickets entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return  entityManager.find (Tickets.class, entity.getId ());
	}

	@Override
	public Set<Tickets> findByIds (List<Long> ticketIds) {
		TypedQuery<Tickets> query = entityManager.createQuery("select tu from Tickets tu where tu.id in (:ticketIds)", Tickets.class);
		query.setParameter("ticketIds", ticketIds);
		return new LinkedHashSet<> (query.getResultList());
	}
}
