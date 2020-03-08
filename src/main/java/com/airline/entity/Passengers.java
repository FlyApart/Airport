package com.airline.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;


import javax.persistence.*;
import java.sql.Timestamp;
/*@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)*/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode(of = "id")//!!!!!!!!!!!!
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
//@DynamicUpdate
@Entity
@Table (name = "passengers")
public class Passengers {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	Long id;
	@Column (name = "name")
	String name;
	@Column (name = "surname")
	String secondName;
	@Column
	String login;
	@Column
	String password;
	@Column (name = "id_country")
	Long country;
	@Column
	Timestamp created;
	@Column
	Timestamp changed;
	@Column (name = "date_birth")
	Timestamp birthDate;
 // void test() {Passengers passengers = Passengers.builder ().id(1L).name("sad").build (); }
}
