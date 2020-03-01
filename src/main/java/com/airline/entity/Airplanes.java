package com.airline.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
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
public class Airplanes {
	Long id;
	String model;
	Long seats;
	Time flightDuration;
	Timestamp built;
	Long airlineId;
}
