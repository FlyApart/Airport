package com.airport.controller.request.change;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

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

    @Pattern(regexp = "^[a-zA-Z]{3,25}$", message = "example : Russia")
    String name;

    @Size (max = 50)
    @Pattern(regexp = "^[\\d]+$", message = "example : 123")
    String population;

    @Pattern(regexp = "^[\\d]+\\.[\\d]{4,6} [\\d]+\\.[\\d]{4,6}", message = "example 52.0157 55.2073")
    String location;

}
