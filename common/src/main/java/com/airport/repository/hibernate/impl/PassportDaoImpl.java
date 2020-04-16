package com.airport.repository.hibernate.impl;

import com.airport.entity.Passports;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.hibernate.PassportDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PassportDaoImpl implements PassportDao {

	private final EntityManager entityManager;

	@Override
	public List<Passports> findAll () {
		return entityManager.createQuery ("select p from Passports p", Passports.class)
		                    .getResultList ();
	}

	@Override
	public Passports findById (Long id) {
		Optional<Passports> passportsOptional = Optional.ofNullable (entityManager.find (Passports.class, id));
		return passportsOptional.orElseThrow (EntityNotFoundException::new);
	}

	@Override
	public void delete (Long id) {
		entityManager.remove (findById (id));
	}

	@Override
	public Passports save (Passports entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Passports.class, entity.getId ());
	}

	@Override
	public Passports update (Passports entity) {
		entityManager.joinTransaction ();
		entityManager.persist (entity);
		return entityManager.find (Passports.class, entity.getId ());
	}


	@Override
	public List<Passports> findByPassengersId (Long passengersId) {
		Optional<List<Passports>> passportsOptional = Optional.ofNullable (entityManager.createQuery ("select p from Passports p " + "where p.passengerId.id =:passengersId", Passports.class)
		                                                                                .
				                                                                                setParameter ("passengersId", passengersId)
		                                                                                .
				                                                                                getResultList ());
		return passportsOptional.orElseThrow (EntityNotFoundException::new);
	}

	@Override
	public Passports findByTitleAndLongPassengersId (String title, Long passengersId) {
		Optional<Passports> optionalPassports = Optional.ofNullable (entityManager.createQuery ("select p from Passports p " + "where p.types =:title and " + "p.passengerId.id =:passengersId", Passports.class)
		                                                                          .setParameter ("title", title)
		                                                                          .setParameter ("passengersId", passengersId)
		                                                                          .getSingleResult ());
		return optionalPassports.orElseThrow (EntityNotFoundException::new);
	}
}
