package com.airport.controller.request.create;

import com.airport.util.validation.FieldValid;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Builder
public class DiscountsSaveRequest {

	@FieldValid
	@Pattern(regexp = "^[\\w]+", message = "example : AUL_125")
	String title;

	@FieldValid
	@Pattern(regexp = "^[\\d]+\\.[\\d]+", message = "example : 123.45")
	String cost;

	Set<Long> flightsId;
}
