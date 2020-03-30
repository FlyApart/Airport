package com.airline.entity;

import com.airline.util.validation.FieldValid;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (exclude = {"id","passengersId"})
@ToString (exclude = {"passengersId"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Entity
@Table (name = "passports", uniqueConstraints = @UniqueConstraint (columnNames = {"series", "number"}))
public class Passports {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column (name = "series", nullable = false)
	Long series;

	@Column (name = "number", nullable = false)
	Long number;

	@Column(name = "title", nullable = false, length = 50)
	String title;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "passengers_id")
	Passengers passengersId;



}
