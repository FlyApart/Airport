package com.airline.controller.request;

import com.airline.util.validation.FieldValid;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (exclude = {"discount"})
@ToString (exclude = {"discount"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
public class FlightSaveRequest extends @NotNull FlightUpdateRequest{

	@FieldValid
	String fightsNumber;



}
