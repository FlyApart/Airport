package com.airport.util;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class ProjectDate {

	public Date getCurrentTime () {
		return new Date ();
	}

	public Date getNextDay (Date date){

		Calendar cal = Calendar.getInstance ();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}
}


