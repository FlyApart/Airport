package com.airline.controller.request;

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

	//@Size (min = 4,max = 50)
	String series;

	//@Size (min = 4,max = 50)
	String number;

	//@Size (min = 4,max = 50)
	String title;

	String id;

	@JsonIgnore
	String passengerId;


}
