package com.airline.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Entity
@Table (name = "airline")
public class Airline {
	@Id
	@SequenceGenerator(name = "airlineSeq", sequenceName = "airline_id_seq", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "airlineSeq")
	Long id;
	@Column
	String name;
	@Column
	String website;
	@Column
	Long fleet;
	@Column(name = "flights_per_year")
	Long flightsCounts;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn (name = "id_country")
	Country country;
}
