package com.airport.controller.request.change;

import com.airport.entity.Countries;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Builder
public class AirplanesUpdateRequest {


	@JsonIgnore
	String id;

    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[\\w]+", message = "example : 12AA_3")
    String model;

    @Size(min = 1, max = 15)
    @Pattern(regexp = "^[\\d]+", message = "example : 123")
    String seats;

    @Size(min = 1, max = 15)
    @Pattern(regexp = "^[\\d]+", message = "example : 123")
    String flightDuration;

    @Past
    Date built;

    @Size(min = 3, max = 50)
    String country;

}
