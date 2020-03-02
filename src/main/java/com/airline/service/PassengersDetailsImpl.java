package com.airline.service;

import com.airline.entity.Passengers;
import com.airline.entity.vo.PassengersPassports;
import com.airline.repository.PassengersDao;
import com.airline.repository.PassportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassengersDetailsImpl  implements PassengersDetailService{
	@Autowired
	PassportDao passportDao;
	@Autowired
	PassengersDao passengersDao;


	@Override
	public List<PassengersPassports> getPassengersInfo () {
		List <PassengersPassports> info = new ArrayList<> ();
		info.add ((PassengersPassports) passengersDao.findAll ());
		return null;
	}

	@Override
	public PassengersPassports findPassengerById (Long id) {
		PassengersPassports info = new PassengersPassports ();
		info.setPassengers (passengersDao.findById (id));
		info.setPassports (passportDao.findByPassengersId (id)); 
		return info;
	}
}
