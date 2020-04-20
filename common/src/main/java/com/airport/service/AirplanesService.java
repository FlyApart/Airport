package com.airport.service;

import com.airport.entity.Airplanes;
import com.airport.entity.Status;

import java.util.List;

public interface AirplanesService {

	List<Airplanes> searchByParam (String date, String countryName, Status status);
}

