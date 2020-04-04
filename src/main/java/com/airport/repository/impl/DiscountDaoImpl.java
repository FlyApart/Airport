package com.airport.repository.impl;

import com.airport.entity.Discounts;
import com.airport.repository.DiscountDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class DiscountDaoImpl implements DiscountDao {

	private final EntityManager entityManager;

	@Override
	public List<Discounts> findAll () {
		return entityManager.createQuery ("select d from Discounts d", Discounts.class)
		                    .getResultList ();
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
		return entityManager.find (Discounts.class, entity.getId ());
	}

	@Override
	public Discounts update (Discounts entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Discounts.class, entity.getId ());
	}

	@Override
	public Set<Discounts> findByIds (List<Long> discountIds) {
		TypedQuery<Discounts> query = entityManager.createQuery ("select d from Discounts d where d.id in (:discountIds)", Discounts.class);
		query.setParameter ("discountIds", discountIds);
		return new LinkedHashSet<> (query.getResultList ());
	}
}
