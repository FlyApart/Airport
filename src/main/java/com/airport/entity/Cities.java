package com.airport.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode// (exclude = {"id","countries","tickets"})
@ToString// (exclude = {"passports","tickets"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table (name = "cities")
public class Cities {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	Long id;

	@Column (nullable = false, length = 50)
	String name;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn
	Countries countries;



}
