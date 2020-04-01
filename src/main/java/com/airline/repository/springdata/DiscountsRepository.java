package com.airline.repository.springdata;

import com.airline.entity.Discounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DiscountsRepository extends CrudRepository<Discounts, Long>, JpaRepository<Discounts, Long> {
	//Set<Discounts> findByIds(List<Long> discountIds);
}
