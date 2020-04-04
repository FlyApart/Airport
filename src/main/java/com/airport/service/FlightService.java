package com.airport.service;

import com.airport.controller.request.save.FlightSaveRequest;
import com.airport.controller.request.update.FlightUpdateRequest;
import com.airport.entity.Flights;

public interface FlightService {

	Flights save (FlightSaveRequest request);

	Flights update (FlightUpdateRequest request, Long id);
}
