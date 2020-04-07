package com.airport.controller.request.change;

import com.airport.util.validation.FieldValid;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
public class CountriesUpdateRequest {

    @JsonIgnore
    String id;

    @Size(min = 3,max = 25)
    String name;


    String population;

    @Pattern(regexp = "^[\\d]+.[\\d]{4,} [\\d]+.[\\d]{4,}", message = "example 52.0157 55.2073")
    String location;

}
