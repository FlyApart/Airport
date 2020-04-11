package com.airport.controller.request.change;

import com.airport.entity.PassportsTypes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PassportUpdateRequest {

	@JsonIgnore
	String id;

	@Size(min = 4, max = 18)
	@Pattern(regexp = "^[\\d]+$", message = "example : 20212223")
	String series;

	@Pattern(regexp = "^[\\d]+$", message = "example : 20212223")
	@Size(min = 4, max = 18)
	String number;

	PassportsTypes types;

    @Size(min = 1,max = 18)
	@Pattern(regexp = "^[\\d]+$", message = "example : 2")
	String passengerId;


}
