package com.airline.service;

import com.airline.controller.request.PassengerSaveRequest;
import com.airline.controller.request.PassengerUpdateRequest;
import com.airline.entity.Passengers;

public interface PassengersService {

	Passengers save (PassengerSaveRequest request);
	Passengers update (PassengerUpdateRequest request, Long id);
}
