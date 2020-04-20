package com.airport.controller.request.change;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightsUpdateRequest {

	@JsonIgnore
	Long id;

	@Size(min = 4, max = 18)
	@Pattern(regexp = "^[\\w]+$", message = "example : AOs45_A")
	String fightsNumber;

	@FutureOrPresent
	LocalDate departureDate;

	@FutureOrPresent
	LocalDate arriveDate;

	@Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "example : 10:15")
	String departureTime;

	@Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "example : 10:15")
	String arriveTime;

	@Size(min = 3, max = 10)
	@Pattern(regexp = "^[\\d]{1,7}\\.[\\d]{1,2}", message = "example : 123.45")
	String price;

	@Size(min = 1, max = 18)
	@Pattern(regexp = "^[\\d]+$", message = "example : 123")
	String airplaneID;

	@Size(min = 1, max = 50)
	String departureAirportTitle;

	@Size(min = 1, max = 50)
	String arriveAirportTitle;

	@Size(min = 3)
	@Pattern(regexp = "^[a-zA-Z ]+", message = "example : Ural  Airlines")
	String airlinesName;

	Set<Long> discountId;
}
