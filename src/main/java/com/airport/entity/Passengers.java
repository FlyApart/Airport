package com.airport.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (exclude = {"id","passports","tickets","cities"})
@ToString (exclude = {"id","passports","tickets"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Entity
@Table (name = "passengers")
public class Passengers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	Long id;

	@Column (nullable = false, length = 50)
	String name;

	@Column (name = "surname", nullable = false, length = 50)
	String secondName;

	@Column(name = "login", unique = true, nullable = false, length = 50)
	String login;

	@Column (nullable = false, length = 50)
	String password;

	@Column
	Timestamp created;

	@Column
	Timestamp changed;

	@Column (name = "date_birth")
	Date birthDate;


	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn (name = "cities_id")
	Cities cities;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Passports.class, mappedBy = "passengersId",cascade = CascadeType.ALL)
	Set<Passports> passports = Collections.emptySet ();

	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Tickets.class, mappedBy = "passengersId",cascade = CascadeType.ALL)
	Set<Tickets> ticket = Collections.emptySet ();

}
