package com.airport.util.converters.cities;

import com.airport.controller.exceptions.ConversionException;
import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.exceptions.ArgumentOfMethodNotValidException;
import com.airport.controller.request.change.CitiesUpdateRequest;
import com.airport.controller.request.create.CitiesSaveRequest;
import com.airport.entity.Cities;
import com.airport.entity.Countries;
import com.airport.util.converters.EntityConverter;
import lombok.RequiredArgsConstructor;

import javax.persistence.NoResultException;

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

  Countries findCountries(String id) {
    Countries countries;
    try {
      countries =
          entityManager
              .createQuery("select c from Countries c where c.id=:id", Countries.class)
              .setParameter("id", Long.valueOf(id))
              .getSingleResult();
    } catch (NumberFormatException e) {
      throw new ConversionException(
          CitiesSaveRequest.class, Cities.class, id, new ArgumentOfMethodNotValidException(id));
    } catch (NoResultException e) {
      throw new ConversionException(
          CitiesSaveRequest.class,
          Cities.class,
          id,
          new EntityNotFoundException(" id = " + id, Countries.class));
    }
    return countries;
  }
}
