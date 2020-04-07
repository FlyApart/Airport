package com.airport.util.converters;

import com.airport.controller.exceptions.ConversionException;
import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.exceptions.MethodArgumentNotValidException;
import com.airport.controller.request.change.PassportUpdateRequest;
import com.airport.controller.request.create.PassengerSaveRequest;
import com.airport.controller.request.create.PassportSaveRequest;
import com.airport.entity.Cities;
import com.airport.entity.Passengers;
import com.airport.entity.Passports;
import com.airport.util.converters.parent.ConverterRequestPassengers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class ConverterSaveRequestPassenger extends ConverterRequestPassengers<PassengerSaveRequest, Passengers> {

    Cities findCity (PassengerSaveRequest request){
        Cities cities;
        try {
            cities = entityManager.createQuery ("select c from Cities c where c.name=:name", Cities.class)
                    .setParameter ("name", request.getName())
                    .getSingleResult ();
        } catch (NumberFormatException e) {
            throw new ConversionException (PassengerSaveRequest.class, Passengers.class, request,
                    new MethodArgumentNotValidException(request.getCities()));
        } catch (NoResultException e) {
            throw new ConversionException (PassengerSaveRequest.class, Passengers.class, request,
                    new EntityNotFoundException ("City with name = " + request.getCities(), Passports.class));
        }
        return cities;
    }

	@Autowired
	private ConverterSaveRequestPassports converterSaveRequestPassport;

	public ConverterSaveRequestPassenger (BCryptPasswordEncoder passwordEncoder) {
		super (passwordEncoder);
	}

	@Override
	public Passengers convert (PassengerSaveRequest request) {

		Passengers passengers = new Passengers ();

		Set<Passports> passportsSet = new HashSet<> ();
		for (PassportSaveRequest p : request.getPassportSaveRequest ()) {
			passportsSet.add (converterSaveRequestPassport.convert (p));
		}
		passengers.setPassports (passportsSet);

		passengers.setCities (findCity(request));

		return doConvert (passengers, request);
	}
}

