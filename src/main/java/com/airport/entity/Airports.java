package com.airport.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (exclude = {"id","country"})
@ToString (exclude = "country")
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Entity
@Table(name = "airports")
public class Airports {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(length = 75, nullable = false, unique = true)
	String title;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn
	Cities cities;
}
