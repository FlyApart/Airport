package com.airline.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Entity
@Table(name = "tickets")
public class Tickets {
	@Id
	@SequenceGenerator (name = "ticketsSeq", schema = "tickets_id_seq",allocationSize = 0)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "ticketsSeq")
	Long id;
	@Column
	String place;
	@Column (name = "total_price")
	Double totalPrice;
	@Column
	Boolean reservation;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	Flights flights;

	@JsonBackReference
	@ManyToMany
	@JoinTable(name = "passengers_ticket",
			joinColumns = @JoinColumn(name = "ticket_id"),
			inverseJoinColumns = @JoinColumn(name = "passenger_id"))
	Set<Passengers> passengers = Collections.emptySet ();
}
