package com.airline.repository.impl;

import com.airline.entity.Discounts;
import com.airline.entity.Tickets;
import com.airline.repository.DiscountDao;
import com.airline.repository.TicketsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Repository
public class DiscountDaoImpl implements DiscountDao {

	@Autowired
	private EntityManager entityManager;



	@Override
	public List<Discounts> findAll () {
			return entityManager.createQuery ("select d from Discounts d", Discounts.class).getResultList ();
	}

	@Override
	public Discounts findById (Long id) {

			return entityManager.find (Discounts.class, id);

	}

	@Override
	public void delete (Long id) {

		entityManager.remove (findById (id));
	}

	@Override
	public Discounts save (Discounts entity) {
		entityManager.joinTransaction ();
			entityManager.persist (entity);
			return  entityManager.find (Discounts.class, entity.getId ());
	}

	@Override
	public Discounts update (Discounts entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return  entityManager.find (Discounts.class, entity.getId ());
	}

	@Override
	public Set<Discounts> findByIds (List<Long> discountIds) {
		TypedQuery<Discounts> query = entityManager.createQuery("select d from Discounts d where d.id in (:discountIds)", Discounts.class);
		query.setParameter("discountIds", discountIds);
		return new LinkedHashSet<> (query.getResultList());
	}
}
