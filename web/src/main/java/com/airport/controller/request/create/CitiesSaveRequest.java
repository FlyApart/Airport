package com.airport.controller.request.create;

import com.airport.util.validation.FieldValid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CitiesSaveRequest {


	@FieldValid(min = 3)
	@Pattern(regexp = "^[a-zA-Z]{3,25}$", message = "example : Moscow")
	String name;

	@FieldValid(min = 3)
	String country;

	@NotNull
	@NotEmpty
	@Pattern(regexp = "^[\\d]{1,3}\\.[\\d]{2,4}$", message = "example : 155.2073")
	String longitude;

	@NotNull
	@NotEmpty
	@Pattern(regexp = "^[\\d]{1,3}\\.[\\d]{2,6}$", message = "example : 155.2073")
	String latitude;
}
