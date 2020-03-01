package com.airline.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
public class Flights {
	Long id;
	String fightsNumber;
	Timestamp departureDate;
	Timestamp arriveDate;
	Long airplanesId;
	Long airportId;
	Long discountId;

}
