package com.airport.util.converters.cities;

import com.airport.controller.exceptions.ArgumentOfMethodNotValidException;
import com.airport.controller.exceptions.ConversionException;
import com.airport.controller.exceptions.EntityAlreadyExistException;
import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.change.CitiesUpdateRequest;
import com.airport.controller.request.create.CitiesSaveRequest;
import com.airport.entity.Cities;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;

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
