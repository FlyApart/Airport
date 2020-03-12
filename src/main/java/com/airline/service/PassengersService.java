package com.airline.service;

import com.airline.controller.request.PassengerRequest;
import com.airline.entity.Passengers;
import com.airline.entity.vo.PassengersInfo;
import com.airline.repository.GenericDao;

import java.util.List;

public interface PassengersService {

	Passengers save (PassengerRequest request);
	Passengers update (PassengerRequest request, Long id);
}
