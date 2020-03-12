package com.airline.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "discounts")
public class Discounts {
	@Id
	@SequenceGenerator(name = "discountsSeq", sequenceName = "discounts_id_seq", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discountsSeq")
	Long id;
	@Column
	String title;
	@Column
	Double cost;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable (name = "flights_discounts",
			joinColumns = @JoinColumn(name = "discounts_id"),
			inverseJoinColumns = @JoinColumn(name = "flights_id"))
	Set<Flights> flights = Collections.emptySet();
}
