package com.airport.service.impl;

import com.airport.entity.Airplanes;
import com.airport.entity.Status;
import com.airport.exceptions.ArgumentOfMethodNotValidException;
import com.airport.repository.springdata.AirplanesRepository;
import com.airport.service.AirplanesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirplanesServiceImpl implements AirplanesService {

	private final AirplanesRepository airplanesRepository;

	@Override
	public List<Airplanes> searchByParam (String date, String countryName, Status status) {

		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
		Date builtDate;
		try {
			builtDate = format.parse (date);
		}catch (ParseException e){

			throw new ArgumentOfMethodNotValidException (e.getMessage ());
		}

		Optional<List<Airplanes>> search = airplanesRepository.findByBuiltAfterAndCountries_NameAndStatus (builtDate, countryName, status);

		return search.orElseGet (ArrayList::new);
	}
}
