package com.airline.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode(of = "id")
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Entity
@Table (name = "airline")
public class Airline {
	@Id
	Long id;
	String name;
	String website;
	Long fleet;
	Long country;
	Long flightsCounts;
}
