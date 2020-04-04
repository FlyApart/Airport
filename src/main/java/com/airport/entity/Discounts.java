package com.airport.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (exclude = {"id","flights"})
@ToString (exclude = {"flights"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Entity
@Table(name = "discounts")
public class Discounts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column ( length = 50, nullable = false, unique = true)
	String title;

	@Column(nullable = false)
	Double cost;

	@JsonBackReference
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable (name = "flights_discounts",
			joinColumns = @JoinColumn(name = "discounts_id"),
			inverseJoinColumns = @JoinColumn(name = "flights_id"))
	Set<Flights> flights = Collections.emptySet();
}
