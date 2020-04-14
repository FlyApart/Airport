package com.airport.repository.hibernate.impl;

import com.airport.entity.Role;
import com.airport.repository.hibernate.RoleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class RoleDaoImpl implements RoleDao {
	@Autowired
	EntityManager entityManager;

	@Override
	public List<Role> getRolesByPassengersId (Long passengersId) {
		return null;
	}

	@Override
	public List<Role> findAll () {
		return null;
	}

	@Override
	public Role findById (Long id) {
		return null;
	}

	@Override
	public void delete (Long id) {

	}

	@Override
	public Role save (Role entity) {
		return null;
	}

	@Override
	public Role update (Role entity) {
		return null;
	}

}
