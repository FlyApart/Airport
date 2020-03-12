package com.airline.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table (name = "airplanes")
public class Airplanes {
	@Id
	@SequenceGenerator(name = "airplanesSeq", sequenceName = "airplanes_id_seq", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "airplanesSeq")
	Long id;
	@Column
	String model;
	@Column
	Long seats;
	@Column(name = "max_flight_duration")
	Time flightDuration;
	@Column
	Timestamp built;

}
