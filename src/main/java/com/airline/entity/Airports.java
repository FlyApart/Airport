package com.airline.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "airports")
public class Airports {
	@Id
	@SequenceGenerator(name = "airportsSeq", sequenceName = "airports_id_seq", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "airportsSeq")
	Long id;
	@Column
	String title;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn (name = "id_country")
	Country country;
}
