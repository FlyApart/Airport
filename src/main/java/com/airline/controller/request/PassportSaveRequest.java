package com.airline.controller.request;

import com.airline.util.validation.FieldValid;
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

	String series;

	String number;

	@FieldValid
	String title;

}
