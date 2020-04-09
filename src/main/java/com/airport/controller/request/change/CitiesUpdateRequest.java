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
public class CitiesUpdateRequest {
    @JsonIgnore
    String id;

    @Size
    @Pattern(regexp = "^[a-zA-Z]{3,25}$", message = "example : Moscow")
    String name;

    @Size(min = 1, max = 15)
    @Pattern(regexp = "^[\\d]{1,15}$", message = "example : 123")
    String countriesId;
}
