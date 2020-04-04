package com.airport.repository.springdata;

import com.airport.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long>, JpaRepository<Role, Long>  {
	//public List <Role> getRolesByPassengersId (Long passengersId);
}
