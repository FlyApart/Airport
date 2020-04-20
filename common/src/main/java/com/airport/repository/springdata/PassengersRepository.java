package com.airport.repository.springdata;

import com.airport.entity.Passenger;
import com.airport.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PassengersRepository extends CrudRepository<Passenger, Long>, JpaRepository<Passenger, Long> {


	Optional<Passenger> findByLogin (String login);

	Optional<Passenger> findByActivationCode (String code);

	@Query("select count(role) from Passenger p join p.role role where role.roleName = :roleName group by role")
	Optional<Integer> countByRole (RoleName roleName);

	//Optional<List<Passenger>>

}