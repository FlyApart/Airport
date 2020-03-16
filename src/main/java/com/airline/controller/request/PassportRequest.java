package com.airline.controller.request;

import com.airline.entity.Passengers;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PassportRequest {

	@NonNull @Size(min = 4, max = 10)
	Long series;
	@NonNull @Size(min = 4, max = 10)
	Long number;
	@NonNull @Size(min = 4, max = 50)
	String title;
}
