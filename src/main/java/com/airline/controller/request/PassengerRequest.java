package com.airline.controller.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PassengerRequest {
	@NonNull @Size(min = 4, max = 50) String name;
	@NonNull @Size(min = 4, max = 50) String secondName;
	@Email @NonNull @Size(min = 4, max = 50) String login;
	String password;
	Timestamp birthDate;
	String country;
	/*Passport*/

	@NonNull Long series;
	@NonNull Long number;


}
