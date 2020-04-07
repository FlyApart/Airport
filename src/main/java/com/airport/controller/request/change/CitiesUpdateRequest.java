package com.airport.controller.request.change;

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
public class CitiesUpdateRequest {
    @JsonIgnore
    String id;

    @Size(min = 5, max = 50)
    String name;

    String countriesId;
}
