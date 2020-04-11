package com.airport.controller.request.change;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AirportsUpdateRequest {

	@JsonIgnore
	String id;

    @Size(min = 1, max = 50)
    String title;

    @Size(min = 3, max = 50)
    String cities;

}
