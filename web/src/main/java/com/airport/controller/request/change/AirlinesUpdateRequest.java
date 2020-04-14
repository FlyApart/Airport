package com.airport.controller.request.change;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AirlinesUpdateRequest {

	@Size (min = 3, max = 50)
	String name;

	@Pattern(regexp = "^[\\w]+\\.[\\w]+", message = "example.com")
	@Size(min = 3,max = 50)
	String website;

	@Size(min = 1, max = 18)
	@Pattern(regexp = "^[\\d]+", message = "example : 123")
	String sumFleet;

	@Size(min = 1, max = 18)
	@Pattern(regexp = "^[\\d]+", message = "example : 123")
	String flightsCounts;

	@Size(min = 3, max = 50)
	String country;

	@JsonIgnore
	String id;

}
