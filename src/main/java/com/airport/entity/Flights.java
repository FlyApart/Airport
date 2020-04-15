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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
	Date departureDate;

	@Column(name = "arrive_date", nullable = false)
	Date arriveDate;

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

	@JsonManagedReference
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "flights_discounts",
			joinColumns = @JoinColumn(name = "flights_id"),
			inverseJoinColumns = @JoinColumn(name = "discounts_id"))
	Set<Discounts> discount = Collections.emptySet ();
}
