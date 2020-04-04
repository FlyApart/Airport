package com.airport.controller.request.update;

import com.airport.util.validation.FieldValid;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode //(callSuper = false)
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
public class PassportUpdateRequest {//extends PassportSaveRequest{

	@FieldValid
	String series;

	@FieldValid
	String number;

	@FieldValid
	String title;

	String id;

	@JsonIgnore
	String passengerId;


}
