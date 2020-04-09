package com.airport.controller.request.create;

import com.airport.util.validation.FieldValid;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
public class CitiesSaveRequest {


    @FieldValid
    @Pattern(regexp = "^[a-zA-Z]{3,25}$", message = "example : Moscow")
    String name;

    @FieldValid(min = 1, max = 15)
    @Pattern(regexp = "^[\\d]{1,15}$", message = "example : 2")
    String countriesId;
}
