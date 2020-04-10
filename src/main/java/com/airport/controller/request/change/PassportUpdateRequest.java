package com.airport.controller.request.change;

import com.airport.entity.PassportsTypes;
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
public class PassportUpdateRequest {


	@Size(min = 4, max = 50)
	@Pattern(regexp = "^[\\d]+$", message = "example : 20212223")
	String series;

	@Pattern(regexp = "^[\\d]+$", message = "example : 20212223")
	@Size(min = 4, max = 50)
	String number;

	PassportsTypes types;

    @JsonIgnore
	String id;

	@Pattern(regexp = "^[\\d]+$", message = "example : 2")
	String passengerId;


}
