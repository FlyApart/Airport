package com.airport.controller.request.create;

import com.airport.util.validation.FieldValid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AirplanesSaveRequest {

	@FieldValid(min = 3)
	@Pattern(regexp = "^[\\w]+", message = "example : 12AA3")
	String model;

	@FieldValid(min = 1, max = 3)
	@Pattern(regexp = "^[\\d]+", message = "example : 123")
	String seats;

	@FieldValid(min = 1, max = 2)
	@Pattern(regexp = "^[\\d]+", message = "example : 123")
	String row;

	@Size(max = 3)
	@Pattern(regexp = "^[\\d]+", message = "example : 123")
	String ComfortSeats;

	@Size(max = 2)
	@Pattern(regexp = "^[\\d]+", message = "example : 5")
	String ComfortRow;

	@Size(max = 3)
	@Pattern(regexp = "^[\\d]+", message = "example : 123")
	String businessSeats;

	@Size(max = 2)
	@Pattern(regexp = "^[\\d]+", message = "example : 5")
	String businessRow;

	@FieldValid(min = 1, max = 18)
	@Pattern(regexp = "^[\\d]+", message = "example : 123")
	String flightDuration;

	@Past
	Date built;

	@FieldValid(min = 3)
	String country;

}


