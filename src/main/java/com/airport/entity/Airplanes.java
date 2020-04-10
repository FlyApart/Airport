package com.airport.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (exclude = {"id","country"})
@ToString (exclude = "country")
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Entity
@Table (name = "airplanes")
public class Airplanes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(length = 50, nullable = false, unique = true)
	String model;

	@Column
	Long seats;

	@Column(name = "number_of_row")
	Long row;

	@Column (name = "comfort_seats")
	Long ComfortSeats;

	@Column(name = "comfort_number_of_row")
	Long ComfortRow;

	@Column (name = "business_seats")
	Long businessSeats;

	@Column(name = "business_number_of_row")
	Long businessRow;


	@Column(name = "max_flight_duration")
	Long flightDuration;

	@Column
    Date built;


	@OneToOne
	@JoinColumn
	Countries countries;

}
