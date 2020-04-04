package com.airline.controller.request;

import com.airline.util.validation.FieldValid;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.sql.Timestamp;
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
	Timestamp birthDate;

	@FieldValid
	String country;

	@NotNull
	Set<PassportSaveRequest> passportSaveRequest;

	/*@NotNull
	List<Long> tickets = Collections.emptyList ();*/



}
