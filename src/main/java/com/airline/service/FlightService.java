package com.airline.service;

import com.airline.controller.request.FlightSaveRequest;
import com.airline.controller.request.FlightUpdateRequest;
import com.airline.entity.Flights;

public interface FlightService {

	Flights save (FlightSaveRequest request);

	Flights update (FlightUpdateRequest request, Long id);
}
