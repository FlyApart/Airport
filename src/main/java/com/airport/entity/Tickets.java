package com.airport.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (exclude = {"id","flights","passengersId"})
@ToString (exclude = {"flights","passengersId"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Entity
@Table(name = "tickets")
public class Tickets {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column (nullable = false, length = 10)
	String place;

	@Column (name = "total_price", nullable = false)
	Double totalPrice;

	@Enumerated(EnumType.STRING)
	@Column(name = "class", nullable = false, length = 50)
	FlightsClass flightsClass =FlightsClass.NORMAL;

	@Column
	Boolean reservation;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (nullable = false)
	Flights flights;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Passengers.class ,cascade = CascadeType.ALL)
	@JoinColumn (name = "passengers_id",nullable = false)
	Passengers passengersId;

}
