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
public class CitiesUpdateRequest {
	@JsonIgnore
	String id;

	@Size(min = 3, max = 50)
	@Pattern(regexp = "^[a-zA-Z]{3,25}$", message = "example : Moscow")
	String name;

	@Size(min = 3, max = 50)
	String country;
}
