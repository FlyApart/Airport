package com.airline.controller.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (exclude = {"passportRequestSet"})
@ToString (exclude = {"passportRequestSet"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
public class PassengerUpdateRequest extends  PassengerSaveRequest{
	Long id;
}
