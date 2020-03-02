package com.airline.service;

import com.airline.entity.vo.PassengersPassports;

import java.util.List;

public interface PassengersDetailService {
	List <PassengersPassports> getPassengersInfo();
	PassengersPassports findPassengerById(Long id);
}
