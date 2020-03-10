package com.airline.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode(of = "id")
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
//@DynamicUpdate
@Entity
@Table (name = "passengers")
public class Passengers {
	@Id
	@SequenceGenerator(name = "passengersSeq", sequenceName = "passengers_id_seq", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passengersSeq")
	Long id;
	@Column (name = "name")
	String name;
	@Column (name = "surname")
	String secondName;
	@Column
	String login;
	@Column
	String password;
	@Column
	Timestamp created;
	@Column
	Timestamp changed;
	@Column (name = "date_birth")
	Timestamp birthDate;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn
	Country country;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Passports.class, mappedBy = "passengersId",cascade = CascadeType.ALL)
	Set<Passports> passports;

	// void test() {Passengers passengers = Passengers.builder ().id(1L).name("sad").build (); }
}
