package com.airline.controller.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PassengerUpdateRequest {
	@Size(min = 4, max = 50) String name;
	@Size(min = 4, max = 50) String secondName;

	String password;
	Timestamp birthDate;
	String country;
	/*Passport*/

	Set<PassportRequest> passportRequestSet;


}
