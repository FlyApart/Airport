package com.airport.controller.request.create;

import com.airport.util.validation.FieldValid;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AirportsSaveRequest {

    @FieldValid(min = 1)
    String title;

    @FieldValid(min = 3)
    String cities;

}
