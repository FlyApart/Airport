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
public class CountriesSaveRequest {

	@FieldValid(min = 3, max = 25)
	@Pattern(regexp = "^[a-zA-Z]{3,25}$", message = "example : Russia")
	String name;

	@FieldValid(min = 1, max = 18)
	@Pattern(regexp = "^[\\d]+$", message = "example : 123")
	String population;
}
