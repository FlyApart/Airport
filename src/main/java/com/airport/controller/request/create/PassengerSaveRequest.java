package com.airport.controller.request.create;

import com.airport.util.validation.FieldValid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PassengerSaveRequest{

	@FieldValid
    @Email
	String login;

	@FieldValid(min = 2)
	@Pattern(regexp = "^[a-zA-Z ]{2,50}$", message = "example : Alex")
	String name;

	@FieldValid(min = 2)
	@Pattern(regexp = "^[a-zA-Z]{2,50}$", message = "example : Cruise")
	String secondName;

	@FieldValid (min = 6,max = 50)
	@Pattern(regexp = "(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}", message = "example : Ar2%fd*")
	String password;

	@Past
	@NotNull
	Date birthDate;

    @FieldValid(min = 3, max = 50)
    String cities;

	@NotNull
    @NotEmpty
	Set<PassportSaveRequest> passportSaveRequest = Collections.emptySet ();
}
