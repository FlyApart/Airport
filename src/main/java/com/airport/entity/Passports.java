package com.airport.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

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
	@Column(unique = true, nullable = false)
	Long id;

	@Column (name = "series", nullable = false)
	Long series;

	@Column (name = "number", nullable = false)
	Long number;

    //TODO Change to enum 26.03.2020 1-30-00
	@Enumerated(EnumType.STRING)
	@Column(name = "types", nullable = false, length = 50)
	PassportsTypes types = PassportsTypes.NOT_SELECTED;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Passengers.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "passengers_id", nullable = false)
	Passengers passengersId;

}
