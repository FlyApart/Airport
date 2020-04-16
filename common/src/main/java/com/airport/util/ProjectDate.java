package com.airport.util;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class ProjectDate {

	public Date getCurrentTime () {
		return new Date ();
	}

	public Date generateTokenExpirationDate (Integer value) {
		Calendar calendar = Calendar.getInstance ();
		calendar.add (Calendar.MILLISECOND, value);
		return calendar.getTime ();
	}


}


