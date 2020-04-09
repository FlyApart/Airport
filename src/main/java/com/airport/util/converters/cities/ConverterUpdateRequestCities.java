package com.airport.util.converters.cities;

import com.airport.controller.exceptions.ArgumentOfMethodNotValidException;
import com.airport.controller.exceptions.ConversionException;
import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.request.change.CitiesUpdateRequest;
import com.airport.entity.Cities;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;

import static java.util.Optional.ofNullable;

@Component
public class ConverterUpdateRequestCities extends ConverterRequestCities <CitiesUpdateRequest, Cities> {

    Cities findById (Cities cities, CitiesUpdateRequest request) {
        cities = ofNullable (entityManager.find (Cities.class, Long.valueOf (request.getId ())))
                .orElseThrow (() -> new ConversionException (CitiesUpdateRequest.class, Cities.class, request,
                        new EntityNotFoundException(Cities.class, request.getId ())));
        return cities;
    }

    Cities findCitiesByName (Cities cities, CitiesUpdateRequest request){
       try{
           cities= entityManager.createQuery ("select c from Cities c where c.name =:name ", Cities.class)
                .setParameter ("name", request.getName ())
                .getSingleResult ();
           }
     catch (NumberFormatException e) {
        throw new ConversionException (CitiesUpdateRequest.class, Cities.class, request,
                new ArgumentOfMethodNotValidException (request));
    } catch (NoResultException e) {
        throw new ConversionException (CitiesUpdateRequest.class, Cities.class, request,
                new EntityNotFoundException (" Cities with name = " + request.getName(), Cities.class));
    }
        return cities;
    }


	@Override
	public Cities convert (CitiesUpdateRequest request) {
        Cities cities = new Cities ();

        if (request.getId()!=null){
            cities = findById(cities, request);
        }
        else if (request.getName()!=null){
            cities = findCitiesByName(cities,request);
            }
        else {
            throw new ConversionException (CitiesUpdateRequest.class, Cities.class, request,
                    new EntityNotFoundException (request, Cities.class));
            }
        cities.setCountries(findCountries(request.getClass (),request.getCountriesId()));
        return doConvert (cities, request);
        }
}
