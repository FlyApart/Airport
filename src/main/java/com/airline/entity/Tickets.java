package com.airline.entity;

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
@EqualsAndHashCode (exclude = {"id","flights","passengers"})
@ToString (exclude = {"flights","passengers"})
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

	@Column
	Boolean reservation;

	@ManyToOne(cascade = CascadeType.ALL/*{CascadeType.MERGE, CascadeType.PERSIST}*/, fetch = FetchType.EAGER)
	@JoinColumn (nullable = true)
	Flights flights;

	//static Long count;

	@JsonBackReference
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "passengers_ticket",
			joinColumns = @JoinColumn(name = "ticket_id"),
			inverseJoinColumns = @JoinColumn(name = "passenger_id"))
	Set<Passengers> passengers = Collections.emptySet ();
}
