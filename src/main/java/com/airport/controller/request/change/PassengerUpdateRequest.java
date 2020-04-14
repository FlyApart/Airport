package com.airport.controller.request.change;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PassengerUpdateRequest {

	@Size(min = 2, max = 50)
	@Pattern(regexp = "^[a-zA-Z ]{2,50}$", message = "example : Alex")
	String name;

	@Size(min = 2, max = 50)
	@Pattern(regexp = "^[a-zA-Z]{2,50}$", message = "example : Cruise")
	String secondName;

	@Size(min = 6, max = 50)
	@Pattern(regexp = "(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}", message = "example : Ar2%fd*")
	String password;

	@Past @NotNull
	Date birthDate;

	@Size(min = 3, max = 50)
	String cities;

	Set<PassportUpdateRequest> passportUpdateRequest;

	Set<Long> tickets = Collections.emptySet ();

	@JsonIgnore
	String id;
}
