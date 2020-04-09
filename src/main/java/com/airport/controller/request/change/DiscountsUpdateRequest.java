package com.airport.controller.request.change;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Builder
public class DiscountsUpdateRequest {

	@JsonIgnore
	String id;

	@Size
	@Pattern(regexp = "^[\\w]+", message = "example : AUL_125")
	String title;

	@Size
	@Pattern(regexp = "^[\\d]+\\.[\\d]+", message = "example : 123,45")
	String cost;

	Set<Long> flightsId;
}
