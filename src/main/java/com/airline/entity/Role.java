package com.airline.entity;

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
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	Long id;

	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn (name = "passengers_id")
	Passengers passengerId;
	@Column
	String role;
}
