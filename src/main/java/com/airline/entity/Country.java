package com.airline.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table (name = "country")
public class Country {
	@Id
	@SequenceGenerator(name = "countrySeq", sequenceName = "country_id_seq", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "countrySeq")
	Long id;
	@Column (name = "name")
	String name;
	@Column (name = "population")
	Long population;
	@Column (name = "location")
	String location;

//	@OneToOne (targetEntity = Passengers.class, mappedBy = "country")
//	private Passengers pas = new Passengers ();

}
