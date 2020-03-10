package com.airline.repository;


import com.airline.entity.Country;
import com.airline.entity.Passports;

import java.util.List;


public interface CountryDao extends GenericDao <Country, Long> {
	public Country findByName(String name);
}
