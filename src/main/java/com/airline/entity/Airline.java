package com.airline.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Airline {
	Long id;
	String name;
	String website;
	Long fleet;
	Long country;
	Long flightsCounts;
}
