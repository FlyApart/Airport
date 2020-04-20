package com.airport.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(exclude = {"discount", "id", "airplane", "departureAirport", "arriveAirport", "airline"})
@ToString(exclude = {"discount", "airplane", "departureAirport", "arriveAirport", "airline"})
@Entity
@Table(name = "flights")
public class Flights {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "flights_number", nullable = false, length = 50, unique = true)
	String fightsNumber;

	@Column(name = "departure_date", nullable = false)
	LocalDate departureDate;

	@Column(name = "departure_time", nullable = false)
	LocalTime departureTime;

	@Column(name = "arrive_date", nullable = false)
	LocalDate arriveDate;

	@Column(name = "arrive_time", nullable = false)
	LocalTime arriveTime;

	@Column(nullable = false)
	Double price;

	@Column
	Date changed;

	@OneToOne
	@JoinColumn(name = "airplane_id", nullable = false)
	Airplanes airplane;

	@OneToOne
	@JoinColumn(name = "departure_airport_id")
	Airports departureAirport;

	@OneToOne
	@JoinColumn(name = "arrive_airport_id")
	Airports arriveAirport;

	@OneToOne
	@JoinColumn(name = "airline_id", nullable = false)
	Airline airline;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	Status status = Status.ACTIVE;

	@JsonManagedReference
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "flights_discounts",
			joinColumns = @JoinColumn(name = "flights_id"),
			inverseJoinColumns = @JoinColumn(name = "discounts_id"))
	Set<Discounts> discount = Collections.emptySet ();

	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Tickets.class, mappedBy = "flights", cascade = CascadeType.ALL)
	Set<Tickets> ticket = Collections.emptySet ();
}
