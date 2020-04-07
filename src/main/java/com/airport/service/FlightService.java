package com.airport.service;

import com.airport.controller.request.change.FlightUpdateRequest;
import com.airport.controller.request.create.FlightSaveRequest;
import com.airport.entity.Flights;

public interface FlightService {

	Flights save (FlightSaveRequest request);

	Flights update (FlightUpdateRequest request, Long id);
}
