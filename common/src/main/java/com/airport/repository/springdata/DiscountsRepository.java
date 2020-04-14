package com.airport.repository.springdata;

import com.airport.entity.Discounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DiscountsRepository extends CrudRepository<Discounts, Long>, JpaRepository<Discounts, Long> {
	//Set<Discounts> findByIds(List<Long> discountIds);

	@Modifying
	@Query("delete  from Discounts d where d = :discounts")
	void deleteDiscounts (Discounts discounts);
}
