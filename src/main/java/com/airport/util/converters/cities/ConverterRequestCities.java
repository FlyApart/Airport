package com.airport.util.converters.cities;

import com.airport.controller.request.change.CitiesUpdateRequest;
import com.airport.controller.request.create.CitiesSaveRequest;
import com.airport.entity.Cities;
import com.airport.util.converters.EntityConverter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
// TODO add
public abstract class ConverterRequestCities<S, T> extends EntityConverter<S, T> {

  protected Cities doConvert(Cities cities, CitiesSaveRequest entity) {
    cities.setName(entity.getName());
    return cities;
  }

  protected Cities doConvert(Cities cities, CitiesUpdateRequest entity) {
    if (entity.getName() != null) cities.setName(entity.getName());
    return cities;
  }
}
