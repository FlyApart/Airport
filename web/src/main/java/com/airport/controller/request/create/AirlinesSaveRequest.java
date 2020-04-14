package com.airport.controller.request.create;

import com.airport.util.validation.FieldValid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AirlinesSaveRequest {

	@FieldValid(min = 3, max = 50)
	@Pattern(regexp = "^[a-zA-Z ]+", message = "example : Ural  Airlines")
	String name;

	@Pattern(regexp = "^[\\w]+\\.[\\w]+", message = "example.com")
	@FieldValid(min = 3,max = 50)
	String website;

	@FieldValid(min = 1, max = 18)
	@Pattern(regexp = "^[\\d]+", message = "example : 123")
	String sumFleet;

	@FieldValid(min = 1, max = 18)
	@Pattern(regexp = "^[\\d]+", message = "example : 123")
	String flightsCounts;

	@FieldValid(min = 1, max = 50)
	String country;
}
