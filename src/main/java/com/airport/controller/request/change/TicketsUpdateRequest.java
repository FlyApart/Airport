package com.airport.controller.request.change;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Builder
public class TicketsUpdateRequest {

	@JsonIgnore
	Boolean reservation;

	@Size(min = 1, max = 10)
	String place;

	@Size (min = 1,max = 50)
	@Pattern(regexp = "^[\\d]+\\.[\\d]+", message = "example : 123.45")
	Double totalPrice;

	@Size (min = 1,max = 50)
	@Pattern(regexp = "^[\\d]+$", message = "example : 123")
	String flightsID;

	@Size (min = 1,max = 50)
	@Pattern(regexp = "^[\\d]+$", message = "example : 123")
	String passengersId;

	@Size (min = 4, max = 50)
	@Pattern(regexp = "^[\\w]+", message = "example : AUL_125")
	String DiscountsTitle;






}
