package com.airport.controller.request.select;

import com.airport.exceptions.ArgumentOfMethodNotValidException;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



@Getter
public class FlightsQueryParams {

	String departureCity;
	String arriveCity;
	LocalDate departureDate;
	LocalDate arriveDate;

	public FlightsQueryParams (String departureCity, String arriveCity, String departureDate, String arriveDate){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("yyyy-MM-dd");
		this.departureCity = departureCity;
		this.arriveCity = arriveCity;
		try {

			this.departureDate = LocalDate.parse (departureDate,formatter);
			if (arriveDate!= null){
				this.arriveDate = LocalDate.parse  (arriveDate, formatter);
			}
		} catch (Exception e) {
			throw new ArgumentOfMethodNotValidException (FlightsQueryParams.class);
		}
	}

}
