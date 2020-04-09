package com.airport.controller.request.create;

import com.airport.util.validation.FieldValid;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Builder
public class AirlinesSaveRequest {

	@FieldValid(min = 3, max = 50)
	@Pattern(regexp = "^[a-zA-Z ]+", message = "example : Ural  Airlines")
	String name;

	@Pattern(regexp = "^[\\w]+\\.[\\w]+", message = "example.com")
	@FieldValid(min = 3,max = 50)
	String website;

	@FieldValid(min = 1, max = 25)
	@Pattern(regexp = "^[\\d]+", message = "example : 123")
	String sumFleet;

	@FieldValid(min = 1, max = 50)
	@Pattern(regexp = "^[\\d]+", message = "example : 123")
	String flightsCounts;

	@FieldValid(min = 3, max = 50)
	String country;
}
