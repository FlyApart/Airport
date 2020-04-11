package com.airport.controller.request.create;

import com.airport.util.validation.FieldValid;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CitiesSaveRequest {


    @FieldValid(min = 3)
    @Pattern(regexp = "^[a-zA-Z]{3,25}$", message = "example : Moscow")
    String name;

    @FieldValid(min = 3, max = 50)
    String country;
}
