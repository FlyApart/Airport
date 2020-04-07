package com.airport.controller.request.change;

import com.airport.controller.request.create.PassportSaveRequest;
import com.airport.util.validation.FieldValid;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Collections;
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

    @Size (max = 50, min = 4)
	String name;

    @Size (max = 50, min = 4)
	String secondName;

    @Size (max = 50, min = 4)
	String password;

	@Past
	Date birthDate;

	@Size (max = 50, min = 4)
	String cities;

    Set<PassportUpdateRequest> passportUpdateRequest = Collections.emptySet ();

    Set<Long> tickets = Collections.emptySet ();

	@JsonIgnore
	String id;
}
