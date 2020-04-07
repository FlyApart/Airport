package com.airport.controller.request.create;

import com.airport.util.validation.FieldValid;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
public class CountriesSaveRequest {

  @FieldValid(min = 3, max = 25)
  String name;

  @NotNull String population;

  @Pattern(regexp = "^[\\d]+.[\\d]{4,} [\\d]+.[\\d]{4,}", message = "example 52.0157 55.2073")
  @NotNull
  String location;
}
