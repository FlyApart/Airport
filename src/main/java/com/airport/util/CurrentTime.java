package com.airport.util;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

public class CurrentTime {

	private Timestamp currentTime = new Timestamp (System.currentTimeMillis ());

    public Timestamp getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Timestamp currentTime) {
        this.currentTime = currentTime;
    }
}


