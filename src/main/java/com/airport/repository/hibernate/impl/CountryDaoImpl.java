package com.airport.repository.hibernate.impl;

import com.airport.entity.Countries;
import com.airport.repository.hibernate.CountryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CountryDaoImpl implements CountryDao {

	private final EntityManager entityManager;


	@Override
	public List<Countries> findAll () {
		return entityManager.createQuery ("select с from Countries с", Countries.class)
		                    .getResultList ();
	}

	@Override
	public Countries findById (Long id) {
		return entityManager.find (Countries.class, id);
	}

	@Override
	public void delete (Long id) {
		entityManager.remove (findById (id));
	}

	@Override
	public Countries save (Countries entity) {

		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Countries.class, entity.getId ());
	}

	@Override
	public Countries update (Countries entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Countries.class, entity.getId ());
	}


	@Override
	public Countries findByName (String name) {
		TypedQuery<Countries> query = entityManager.createQuery ("select c from Countries c where c.name = :name", Countries.class);
		query.setParameter ("name", name);
		return query.getSingleResult ();
	}





	/*@Override
	public <S extends Countries> S save (S entity) {
		return null;
	}

	@Override
	public <S extends Countries> Iterable<S> saveAll (Iterable<S> entities) {
		return null;
	}

	@Override
	public Optional<Countries> findById (Long aLong) {
		return Optional.empty ();
	}

	@Override
	public boolean existsById (Long aLong) {
		return false;
	}

	@Override
	public Iterable<Countries> findAll () {
		return null;
	}

	@Override
	public Iterable<Countries> findAllById (Iterable<Long> longs) {
		return null;
	}

	@Override
	public long count () {
		return 0;
	}

	@Override
	public void deleteById (Long aLong) {

	}

	@Override
	public void delete (Countries entity) {

	}

	@Override
	public void deleteAll (Iterable<? extends Countries> entities) {

	}

	@Override
	public void deleteAll () {

	}*/
}
