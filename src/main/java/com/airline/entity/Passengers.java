package com.airline.entity;

import com.airline.util.validation.FieldValid;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (exclude = {"id","passports","tickets"})
@ToString (exclude = {"passports","tickets"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Entity
@Table (name = "passengers")
public class Passengers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column (nullable = false, length = 50)
	String name;

	@Column (name = "surname", nullable = false, length = 50)
	String secondName;

	@Column(name = "login", unique = true, nullable = false, length = 50)
	@Email
	String login;

	@Column (nullable = false,length = 50)
	String password;

	//@Temporal (TemporalType.TIMESTAMP)//реаброзует slq timestamp in java timestamp
	@Column
	Timestamp created;

	@Column
	Timestamp changed;

	@Column (name = "date_birth")
	Timestamp birthDate;


	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	Countries countries;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Passports.class, mappedBy = "passengersId",cascade = CascadeType.ALL)
	Set<Passports> passports = Collections.emptySet ();

	@JsonManagedReference
	@ManyToMany
	@JoinTable(name = "passengers_ticket",
			joinColumns = @JoinColumn(name = "passenger_id"),
			inverseJoinColumns = @JoinColumn(name = "ticket_id"))
	Set<Tickets> tickets = Collections.emptySet ();

	// void test() {Passengers passengers = Passengers.builder ().id(1L).name("sad").build (); }
}
