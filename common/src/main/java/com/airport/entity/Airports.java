package com.airport.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "cities"})
@ToString(exclude = "cities")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "airports")
public class Airports {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(length = 75, nullable = false, unique = true)
	String title;

	@OneToOne
	@JoinColumn
	Cities cities;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 50)
	Status status = Status.ACTIVE;
}
