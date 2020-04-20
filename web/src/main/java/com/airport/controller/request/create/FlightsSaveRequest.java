package com.airport.controller.request.create;

import com.airport.util.validation.FieldValid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightsSaveRequest {

	@FieldValid(max = 18)
	@Pattern(regexp = "^[\\w]+$", message = "example : AOs45_A")
	String fightsNumber;

	@FutureOrPresent
	@NotNull
	LocalDate departureDate;

	@NotNull
	@Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "example : 10:15")
	String departureTime;

	@FutureOrPresent
	@NotNull
	LocalDate arriveDate;

	@NotNull
	@Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "example : 10:15")
	String arriveTime;

	@FieldValid(min = 3, max = 10)
	@Pattern(regexp = "^[\\d]{1,7}\\.[\\d]{1,2}", message = "example : 123.45")
	String price;

	@FieldValid(min = 1, max = 18)
	@Pattern(regexp = "^[\\d]+$", message = "example : 123")
	String airplaneID;

	@FieldValid(min = 1)
	String departureAirportTitle;

	@FieldValid(min = 1)
	String arriveAirportTitle;

	@FieldValid(min = 3)
	@Pattern(regexp = "^[a-zA-Z ]+", message = "example : Ural  Airlines")
	String airlinesName;

	Set<Long> discountId;

}