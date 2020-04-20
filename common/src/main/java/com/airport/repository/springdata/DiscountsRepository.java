package com.airport.repository.springdata;

import com.airport.entity.Discounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DiscountsRepository extends CrudRepository<Discounts, Long>, JpaRepository<Discounts, Long> {

	Optional <Discounts> findByTitle (String title);

	Optional<List<Discounts>> findByIdIn (List<Long> ids);

}
