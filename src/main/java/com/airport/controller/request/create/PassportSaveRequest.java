package com.airport.controller.request.create;

import com.airport.entity.PassportsTypes;
import com.airport.util.validation.FieldValid;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
public class PassportSaveRequest {

	@FieldValid
	String series;

	@FieldValid
	String number;

	@NotNull
	PassportsTypes types;

	@NotNull
    @NotEmpty
	String passengerId;


}
