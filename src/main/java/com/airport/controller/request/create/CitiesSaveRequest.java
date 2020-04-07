package com.airport.controller.request.create;

import com.airport.entity.Countries;
import com.airport.util.validation.FieldValid;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
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
    String name;

    String countriesId;
}
