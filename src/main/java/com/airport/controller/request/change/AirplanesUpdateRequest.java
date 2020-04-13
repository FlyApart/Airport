package com.airport.controller.request.change;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AirplanesUpdateRequest {


	@JsonIgnore
	String id;

    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[\\w]+", message = "example : 12AA3")
    String model;

    @Size(min = 1, max = 3)
    @Pattern(regexp = "^[\\d]+", message = "example : 123")
    String seats;

    @Size(min = 1, max = 2)
    @Pattern(regexp = "^[\\d]+", message = "example : 123")
    String row;

    @Size(min = 1, max = 3)
    @Pattern(regexp = "^[\\d]+", message = "example : 123")
    String ComfortSeats;

    @Size(max = 2)
    @Pattern(regexp = "^[\\d]+", message = "example : 5")
    String ComfortRow;

    @Size(min = 1, max = 3)
    @Pattern(regexp = "^[\\d]+", message = "example : 123")
    String businessSeats;

    @Size(max = 2)
    @Pattern(regexp = "^[\\d]+", message = "example : 5")
    String businessRow;

    @Size(min = 1, max = 18)
    @Pattern(regexp = "^[\\d]+", message = "example : 123")
    String flightDuration;

    @Past
    Date built;

    @Size(min = 3, max = 50)
    String country;

}
