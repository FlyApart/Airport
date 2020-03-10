package com.airline.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table (name = "country")
public class Country {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	Long id;
	@Column (name = "name")
	String name;
	@Column (name = "population")
	Long population;
	@Column (name = "location")
	String location;

//	@OneToOne (targetEntity = Passengers.class, mappedBy = "country")
//	private Passengers pas = new Passengers ();

}
