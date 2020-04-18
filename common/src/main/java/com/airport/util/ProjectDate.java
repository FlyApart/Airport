package com.airport.util;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProjectDate {

	public Date getCurrentTime () {
		return new Date ();
	}
}


