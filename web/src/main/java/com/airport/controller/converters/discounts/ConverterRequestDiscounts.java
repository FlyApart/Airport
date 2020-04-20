package com.airport.controller.converters.discounts;

import com.airport.controller.converters.EntityConverter;
import com.airport.controller.request.change.DiscountsUpdateRequest;
import com.airport.controller.request.create.DiscountsSaveRequest;
import com.airport.entity.Discounts;
import com.airport.entity.Flights;
import com.airport.exceptions.ConversionException;
import com.airport.exceptions.EntityAlreadyExistException;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.DiscountsRepository;
import com.airport.repository.springdata.FlightsRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public abstract class ConverterRequestDiscounts<S, T> extends EntityConverter<S, T> {

	private final DiscountsRepository discountsRepository;
	private final FlightsRepository flightsRepository;

	protected Discounts doConvert (Discounts discounts, DiscountsSaveRequest entity) {

		discounts.setCost (Double.valueOf (entity.getCost ()));
		discounts.setTitle (entity.getTitle ());
		return discounts;
	}

	protected Discounts doConvert (Discounts discounts, DiscountsUpdateRequest entity) {

		if (entity.getCost () != null) {
			discounts.setCost (Double.valueOf (entity.getCost ()));
		}

		if (entity.getTitle () != null) {
			discounts.setTitle (entity.getTitle ());
		}
		return discounts;
	}

	Set<Flights> findFlights (Class<?> sClass, Set<Long> flightsId) {
		List<Flights> flightsList = flightsRepository.findByIdIn(new ArrayList<> (flightsId))
		                                             .orElseThrow (()->new ConversionException (sClass, Discounts.class, flightsId, new EntityNotFoundException (Flights.class, flightsId)));
		if (flightsId.size ()!=flightsList.size ()){
			for (Flights flights : flightsList) {
				flightsId.remove (flights.getId ());
			}
			throw new ConversionException (sClass, Discounts.class, flightsId, new EntityNotFoundException (Flights.class, flightsId));
		}
		return new HashSet<> (flightsList);
	}

	void uniqueDiscountsName (Class<?> sClass, String title) {

			boolean unique = discountsRepository.findByTitle (title)
			                                   .isPresent ();
			if (unique){
				throw new ConversionException (sClass, Discounts.class, title, new EntityAlreadyExistException (Discounts.class, "title = "+title));
			}
		}

	Discounts findDiscountsById (Class<?> sClass, Long id) {
		return discountsRepository.findById(id)
		                          .orElseThrow (()-> new ConversionException (sClass, Discounts.class, id,
				                                         new EntityNotFoundException (Discounts.class, id)));

	}
}
