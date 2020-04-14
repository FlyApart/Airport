package com.airport.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (exclude = {"id","passengersId","types"})
@ToString (exclude = {"id","types","passengersId"})
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

	@Enumerated(EnumType.STRING)
	@Column(name = "types", nullable = false, length = 50)
	PassportsTypes types = PassportsTypes.NOT_SELECTED;

	@JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Passengers.class, cascade = CascadeType.ALL)
	@JoinColumn(nullable = false,name = "passengers_id")
	Passengers passengersId;

}
