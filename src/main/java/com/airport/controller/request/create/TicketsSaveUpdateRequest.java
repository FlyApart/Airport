package com.airport.controller.request.create;

import com.airport.entity.SeatsClass;
import com.airport.util.validation.FieldValid;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketsSaveUpdateRequest {

	@JsonIgnore
	Boolean reservation;

	@JsonIgnore
	String id;

	@Pattern(regexp = "^[\\d]{1,2}[A-L]$", message = "example : 9L")
	@Size(min = 1, max = 10)
	String place;

	@FieldValid (min = 1, max = 18)
	@Pattern(regexp = "^[\\d]+$", message = "example : 123")
	String flightsID;

	@FieldValid (min = 1, max = 18)
	@Pattern(regexp = "^[\\d]+$", message = "example : 123")
	String passengersId;

	@Size(min = 4, max = 50)
	@Pattern(regexp = "^[\\w]+", message = "example : AUL_125")
	String DiscountsTitle;

	@NotNull
	SeatsClass seatsClass;
}

