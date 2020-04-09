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
public class AirlinesUpdateRequest {

	@Size (min = 3, max = 50)
	String name;

	@Pattern(regexp = "^[\\w]+\\.[\\w]+", message = "example.com")
	@Size(min = 3,max = 50)
	String website;

	@Size(min = 1, max = 25)
	@Pattern(regexp = "^[\\d]+", message = "example : 123")
	String sumFleet;

	@Size(min = 1, max = 50)
	@Pattern(regexp = "^[\\d]+", message = "example : 123")
	String flightsCounts;

	@Size(min = 3, max = 50)
	String country;

	@JsonIgnore
	String id;

}
