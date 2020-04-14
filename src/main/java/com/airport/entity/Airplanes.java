package com.airport.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "countries"})
@ToString(exclude = "countries")
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Entity
@Table(name = "airplanes")
public class Airplanes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(length = 50, nullable = false, unique = true)
	String model;

	@Column
	Integer seats;

	@Column(name = "number_of_row")
	Integer row;

	@Column(name = "comfort_seats")
	Integer ComfortSeats;

	@Column(name = "comfort_number_of_row")
	Integer ComfortRow;

	@Column(name = "business_seats")
	Integer businessSeats;

	@Column(name = "business_number_of_row")
	Integer businessRow;


	@Column(name = "max_flight_duration")
	Long flightDuration;

	@Column
	Date built;

	@OneToOne
	@JoinColumn
	Countries countries;

}
