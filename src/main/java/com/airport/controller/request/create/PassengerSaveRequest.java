package com.airport.controller.request.create;

import com.airport.entity.Cities;
import com.airport.util.validation.FieldValid;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (exclude = {"passportRequestSet","tickets"})
@ToString (exclude = {"passportRequestSet","tickets"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
public class PassengerSaveRequest{

	@FieldValid
    @Email
	String login;

	@FieldValid
	String name;

	@FieldValid
	String secondName;

	@FieldValid
	String password;

	@Past
	Date birthDate;

	@FieldValid
    String cities;

	Set<PassportSaveRequest> passportSaveRequest = Collections.emptySet ();
}
