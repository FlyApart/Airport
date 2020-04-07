package com.airport.controller.request.change;

import com.airport.entity.PassportsTypes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode //(callSuper = false)
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Builder
public class PassportUpdateRequest {//extends PassportSaveRequest{

	@Size( min = 4, max = 15)
	String series;

	@Size( min = 4, max = 15)
	String number;

	PassportsTypes types = PassportsTypes.NOT_SELECTED;

	String id;

	@JsonIgnore
	String passengerId;


}
