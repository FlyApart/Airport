package com.airline.controller.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (callSuper = false)
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
public class PassportUpdateRequest extends PassportSaveRequest{

	Long id;


}
