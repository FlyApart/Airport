package com.airport.controller.request.create;

import com.airport.util.validation.FieldValid;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiscountsSaveRequest {

	@FieldValid
	@Pattern(regexp = "^[\\w]+", message = "example : AUL_125")
	String title;

	@FieldValid(min = 3, max = 10)
	@Pattern(regexp = "^[\\d]{1,7}\\.[\\d]{1,2}", message = "example : 123.45")
	String cost;

	Set<Long> flightsId;
}
