package com.airline.repository;

import com.airline.entity.Role;

import java.util.List;

public interface RoleDao extends GenericDao<Role, Long> {
	public List <Role> getRolesByPassengersId(Long passengersId);
}
