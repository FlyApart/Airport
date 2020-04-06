package com.airport.controller.request.save;

import com.airport.entity.PassportsTypes;
import com.airport.util.validation.FieldValid;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

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

	//@NotEmpty
	PassportsTypes types = PassportsTypes.NOT_SELECTED;

	String passengerId;


}
