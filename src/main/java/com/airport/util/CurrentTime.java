package com.airport.util;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

public class CurrentTime {

	private Timestamp currentTime;

	public Timestamp getCurrentTime () {
		return  new Timestamp (System.currentTimeMillis ());
	}
}


