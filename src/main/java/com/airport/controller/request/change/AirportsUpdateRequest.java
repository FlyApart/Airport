package com.airport.controller.request.change;

import com.airport.entity.Cities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Builder
public class AirportsUpdateRequest {

	@JsonIgnore
	String id;

    @Size(min = 1, max = 50)
    String title;

    @Size(min = 3, max = 50)
    String cities;

}
