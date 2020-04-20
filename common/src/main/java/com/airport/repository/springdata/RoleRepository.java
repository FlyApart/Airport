package com.airport.repository.springdata;

import com.airport.entity.Role;
import com.airport.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long>, JpaRepository<Role, Long> {

	Optional<Role>findByRoleName (RoleName role);
}
