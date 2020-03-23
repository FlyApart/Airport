package com.airline.repository;


import com.airline.entity.Discounts;
import com.airline.entity.Passengers;
import com.airline.entity.Tickets;

import java.util.List;
import java.util.Set;

public interface DiscountDao extends GenericDao <Discounts, Long> {
	Set<Discounts> findByIds(List<Long> discountIds);
}
