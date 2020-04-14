package com.airport.controller.request;

import com.airport.entity.RoleName;
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
public class RoleRequest {

	@JsonIgnore
	Long id;

	@NotNull RoleName role;

	@Size(min = 1, max = 18)
	@Pattern(regexp = "^[\\d]+", message = "example : 123")
	String passengerId;
}
