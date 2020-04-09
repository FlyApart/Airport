package com.airport.controller.request.create;

import com.airport.util.validation.FieldValid;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
public class CountriesSaveRequest {

  @FieldValid(min = 3, max = 25)
  @Pattern(regexp = "^[a-zA-Z]{3,25}$", message = "example : Russia")
  String name;

  @FieldValid
  String population;

  @Pattern(regexp = "^[\\d]{1,3}+\\.[\\d]{4,6}\\s[\\d]{1,3}\\.[\\d]{4,6}$", message = "example : 152.0157 155.2073")
  @NotNull
  String location;
}