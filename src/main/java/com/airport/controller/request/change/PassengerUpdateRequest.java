package com.airport.controller.request.change;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

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
@DynamicUpdate
public class PassengerUpdateRequest {

    @Size (max = 50, min = 2)
    @Pattern(regexp = "^[a-zA-Z]{2,50}$", message = "example : Alex")
	String name;

    @Size (max = 50, min = 2)
    @Pattern(regexp = "^[a-zA-Z]{2,50}$", message = "example : Cruise")
	String secondName;

    @Size (max = 50, min = 4)
    @Pattern(regexp = "(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}", message = "example : Ar2%fd*")
	String password;

	@Past
	Date birthDate;

	@Size (max = 50, min = 4)
	String cities;

    Set<PassportUpdateRequest> passportUpdateRequest;

    Set<Long> tickets = Collections.emptySet ();

	@JsonIgnore
	String id;
}
