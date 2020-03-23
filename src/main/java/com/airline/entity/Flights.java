package com.airline.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode (exclude = {"discount","id","airplane","departureAirport","arriveAirport","airlines"})
@ToString(exclude = {"discount","id","airplane","departureAirport","arriveAirport","airlines"})
@Entity
@Table(name = "flights")
public class Flights {
	@Id
	@SequenceGenerator(name = "flightsSeq", sequenceName = "flights_id_seq", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flightsSeq")
	Long id;
	@Column(name = "flights_number")
	String fightsNumber;
	@Column(name = "departure_date")
	Timestamp departureDate;
	@Column(name = "arrive_date")
	Timestamp arriveDate;
	@Column
	Double price;
	@Column
	Timestamp changed;

	@OneToOne
	@JoinColumn(name = "airplane_id")
	Airplanes airplane;

	@OneToOne
	@JoinColumn(name = "departure_airport_id")
	Airports departureAirport;

	@OneToOne
	@JoinColumn(name = "arrive_airport_id")
	Airports arriveAirport;

	@OneToOne
	@JoinColumn(name = "airline_id")
	Airline airlines;

	@JsonManagedReference
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable (name = "flights_discounts",
		joinColumns = @JoinColumn(name = "flights_id"),
		inverseJoinColumns = @JoinColumn(name = "discounts_id"))
	Set<Discounts> discount = Collections.emptySet();
}
