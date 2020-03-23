package com.airline.controller.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Collections;
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
public class FlightSaveRequest {

	@NonNull @Size(min = 4, max = 50) String fightsNumber;
	@NonNull Timestamp departureDate;
	@NonNull Timestamp arriveDate;

	@NonNull @Size(min = 4, max = 50) String modelAirplane;
	@NonNull @Size(min = 4, max = 50) String departureAirport;
	@NonNull @Size(min = 4, max = 50) String arriveAirport;
	@NonNull @Size(min = 4, max = 50) String airlines;
	@NonNull  Double price;

	List<Long> discount = Collections.emptyList ();

	/*Set<PassportRequest> passportRequestSet;
	List<Long> tickets = Collections.emptyList ();*/



}
