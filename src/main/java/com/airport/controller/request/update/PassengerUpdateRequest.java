package com.airport.controller.request.update;

import com.airport.util.validation.FieldValid;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.Past;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (exclude = {"passportRequestSet"})
@ToString (exclude = {"passportRequestSet"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
public class PassengerUpdateRequest {//extends  PassengerSaveRequest{

	@FieldValid
	String name;

	@FieldValid
	String secondName;

	@FieldValid
	String password;

	@Past
	Date birthDate;

	@FieldValid
	String country;

	Set<PassportUpdateRequest> passportUpdateRequests;

	String id;
}
