package com.airport.util.converters.passengers;

import com.airport.controller.exceptions.ConversionException;
import com.airport.controller.exceptions.EntityNotFoundException;
import com.airport.controller.exceptions.ArgumentOfMethodNotValidException;
import com.airport.controller.request.create.PassengerSaveRequest;
import com.airport.controller.request.create.PassportSaveRequest;
import com.airport.entity.Cities;
import com.airport.entity.Passengers;
import com.airport.entity.Passports;
import com.airport.util.converters.passports.ConverterSaveRequestPassports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.Set;

@Component
public class ConverterSaveRequestPassenger extends ConverterRequestPassengers<PassengerSaveRequest, Passengers> {

	@Autowired
	private ConverterSaveRequestPassports converterSaveRequestPassport;

	public ConverterSaveRequestPassenger (BCryptPasswordEncoder passwordEncoder) {
		super (passwordEncoder);
	}

    Cities findCity (PassengerSaveRequest request){
        Cities cities;
        try {
            cities = entityManager.createQuery ("select c from Cities c where c.name=:name", Cities.class)
                    .setParameter ("name", request.getCities ())
                    .getSingleResult ();
        } catch (NumberFormatException e) {
            throw new ConversionException (PassengerSaveRequest.class, Passengers.class, request,
                    new ArgumentOfMethodNotValidException(request.getCities()));
        } catch (NoResultException e) {
            throw new ConversionException (PassengerSaveRequest.class, Passengers.class, request,
                    new EntityNotFoundException ("City with name = " + request.getCities(), Passports.class));
        }
        return cities;
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

