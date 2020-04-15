package com.airport.repository.springdata;

import com.airport.entity.Discounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DiscountsRepository extends CrudRepository<Discounts, Long>, JpaRepository<Discounts, Long> {

	Optional <Discounts> findByTitle (String title);

	@Modifying
	@Query("delete  from Discounts d where d = :discounts")
	void deleteDiscounts (Discounts discounts);

	@Query("select d from Discounts d where d.id in :ids")
	Optional<List<Discounts>> findByIds (List<Long> ids);
}
