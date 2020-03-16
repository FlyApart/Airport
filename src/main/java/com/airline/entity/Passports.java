package com.airline.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Entity
@Table (name = "passport")
public class Passports {

	@Id
	@SequenceGenerator(name = "passportsSeq", sequenceName = "passport_id_seq", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passportsSeq")
	Long id;
	@Column (name = "series")
	Long series;
	@Column (name = "number")
	Long number;
	@Column(name = "title")
	String title;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_passengers")
	Passengers passengersId;



}
