package com.airline.controller.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
		String password;
		Long country;
		Timestamp birthDate;

}
