package com.airline.controller.request;

import com.airline.entity.Passports;

import com.airline.entity.Tickets;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PassengerSaveRequest {
	@NonNull @Size(min = 4, max = 50) String name;
	@NonNull @Size(min = 4, max = 50) String secondName;
	@Email @NonNull @Size(min = 4, max = 50) String login;
	String password;
	Timestamp birthDate;
	String country;

	/*Passport*/
	/*@NonNull Long series;
	@NonNull Long number;*/

	Set<PassportRequest> passportRequestSet;

	List<Long> tickets = Collections.emptyList ();



}
