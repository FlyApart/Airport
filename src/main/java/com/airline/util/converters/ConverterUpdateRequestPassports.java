package com.airline.util.converters;

import com.airline.controller.exceptions.NoResultException;
import com.airline.controller.request.PassportUpdateRequest;
import com.airline.entity.Passports;
import com.airline.util.converters.parent.ConverterRequestPassports;
import com.airline.controller.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

//import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import java.util.List;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

@Component
public class ConverterUpdateRequestPassports
    extends ConverterRequestPassports<PassportUpdateRequest, Passports> {


  @Override
  public Passports convert(PassportUpdateRequest request) {
    Passports passports;
    if (request.getId() != null) { // TODO add security
      passports =
          ofNullable(entityManager.find(Passports.class, Long.valueOf(request.getId())))
              .orElseThrow(() -> new EntityNotFoundException(Passports.class, request.getId()));

    } /*else {
      TypedQuery<Passports> optional =
               ofNullable(
                          entityManager
                              .createQuery(
                                  "select p from Passports p where p.title =:title and "
                                      + "p.passengersId.id =:passengersId",
                                  Passports.class)
                              .setParameter("title", request.getTitle())
                              .setParameter("passengersId", Long.valueOf(request.getPassengerId())))
                  .orElseThrow(() -> new RuntimeException());
        if (optional.getResultList().isEmpty()){
            throw new EntityNotFoundException(Passports.class, request.getId());
        }
        else
      passports=ofNullable(optional.getSingleResult()).orElseThrow(() -> new NoResultException());
    }*/

    else {

        TypedQuery<Passports> passportsTypedQuery = entityManager
                .createQuery(
                        "select p from Passports p where p.title =:title and "
                                + "p.passengersId.id =:passengersId",
                        Passports.class)
                .setParameter("title", request.getTitle())
                .setParameter("passengersId", Long.valueOf(request.getPassengerId()));

        if (passportsTypedQuery.getResultList().size() != 1){
            throw new EntityNotFoundException(Passports.class, request.getId());
        }
        passports = passportsTypedQuery.getSingleResult();
    }
    return doConvert(passports, request);
  }
}
