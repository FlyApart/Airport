package com.airport.controller.request.create;

import com.airport.entity.Cities;
import com.airport.util.validation.FieldValid;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Builder
public class AirportsSaveRequest {

    @FieldValid(min = 1, max = 50)
    String title;

    @FieldValid(min = 3, max = 50)
    String cities;

}
