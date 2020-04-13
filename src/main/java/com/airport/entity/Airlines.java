package com.airport.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode (exclude = {"id","flightsCounts","country"})
@ToString (exclude = {"flightsCounts","country"})
@DynamicUpdate
@Entity
@Table (name = "airlines")
public class Airlines {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column (length = 50, nullable = false, unique = true)
	String name;

	@Column(length = 50, nullable = false, unique = true)
	String website;

	@Column
	Long fleet;

	@Column(name = "flights_per_year")
	Long flightsCounts;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn
	Countries countries;
}
