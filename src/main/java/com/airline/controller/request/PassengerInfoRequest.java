package com.airline.controller.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
public class PassengerInfoRequest {
	@NonNull
	@Size(min = 4, max = 50)
	String name;
	@NonNull
	@Size(min = 4, max = 50)
	String secondName;
	@Email
	@NonNull
	@Size(min = 4, max = 50)
	String login;
	@NotNull
	@Size(min = 4, max = 50)
	String password;
	@NonNull
	Long country;
	@NonNull
	Timestamp birthDate;

	/*Passport*/

		@NonNull
	Long series;
	@NonNull
	Long number;

	// country var
	//Long passengersId;
}
