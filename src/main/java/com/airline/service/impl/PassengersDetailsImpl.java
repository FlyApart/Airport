package com.airline.service.impl;

import com.airline.entity.Passengers;
import com.airline.entity.Passports;
import com.airline.entity.vo.PassengersPassports;
import com.airline.repository.PassengersDao;
import com.airline.repository.PassportDao;
import com.airline.service.PassengersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassengersDetailsImpl  implements PassengersDetailService {
	@Autowired
	PassportDao passportDao;
	@Autowired
	PassengersDao passengersDao;


	@Override
	public List<PassengersPassports> getPassengersInfo () {
		List<PassengersPassports> list = new ArrayList<> ();
            for (Passengers passengers : passengersDao.findAll()) {
            	PassengersPassports info = new PassengersPassports ();
                info.setPassengers(passengers);
                info.setPassports (passportDao.findByPassengersId (passengers.getId ()));
                list.add (info) ;
            }
		return list;
	}

	@Override
	public PassengersPassports findPassengerById (Long id) {
		PassengersPassports info = new PassengersPassports ();
		info.setPassengers (passengersDao.findById (id));
		info.setPassports (passportDao.findByPassengersId (id));
		return info;
	}
}
