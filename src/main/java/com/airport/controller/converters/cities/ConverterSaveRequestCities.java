package com.airport.controller.converters.cities;

import com.airport.controller.request.create.CitiesSaveRequest;
import com.airport.entity.Cities;
import org.springframework.stereotype.Component;

@Component
public class ConverterSaveRequestCities extends ConverterRequestCities<CitiesSaveRequest, Cities> {

  @Override
  public Cities convert(CitiesSaveRequest request) {
    Cities cities = new Cities();

    cities.setCountries(findCountries(request.getClass(), request.getCountry()));

    uniqueCitiesName(request.getClass(), request.getName());

    return doConvert(cities, request);
  }
}
