package com.airport.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "flights", "passengerId"})
@ToString(exclude = {"flights", "passengerId"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "tickets")
public class Tickets {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Transient
	String discount;

	@Column(nullable = false, length = 10)
	String place;

	@Column(name = "total_price", nullable = false)
	Double totalPrice;

	@Enumerated(EnumType.STRING)
	@Column(name = "class", nullable = false, length = 50)
	SeatsClass seatsClass = SeatsClass.NORMAL;

	@Column
	Boolean reservation = false;

	@JsonBackReference
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	Flights flights;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "passenger_id", nullable = false)
	Passenger passengerId;

	@JsonIgnore
	@Column (name = "code_activation")
	String activationCode;
}
