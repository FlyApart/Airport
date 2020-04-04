package com.airport.repository;


import com.airport.entity.Discounts;

import java.util.List;
import java.util.Set;

public interface DiscountDao extends GenericDao <Discounts, Long> {
	Set<Discounts> findByIds(List<Long> discountIds);
}
