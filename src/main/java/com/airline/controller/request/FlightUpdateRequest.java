package com.airline.controller.request;

import com.airline.util.validation.FieldValid;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (exclude = {"discount"})
@ToString (exclude = {"discount"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
public class FlightUpdateRequest {

	@FutureOrPresent
	Timestamp departureDate;

	@FutureOrPresent
	Timestamp arriveDate;

	@FieldValid
	String modelAirplane;

	@FieldValid
	String departureAirport;

	@FieldValid
	String arriveAirport;

	@FieldValid
	String airlines;

	@NotNull
	Double price;

	@NotNull
	List<Long> discount = Collections.emptyList ();




}
