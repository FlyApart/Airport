package com.airport.repository.hibernate;

import com.airport.entity.Role;

import java.util.List;

public interface RoleDao extends GenericDao<Role, Long> {
	public List <Role> getRolesByPassengersId(Long passengersId);
}
