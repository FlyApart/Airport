package com.airport.util.validation.impl;

import com.airport.controller.exceptions.MethodArgumentNotValidException;
import com.airport.util.validation.FieldValid;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.lang.invoke.MethodHandles;
import java.time.Clock;

public class FieldValidValidator implements ConstraintValidator<FieldValid, CharSequence> {
	private static final Log LOG = LoggerFactory.make (MethodHandles.lookup ());

	private int min;
	private int max;
	private boolean isNotNull;

	@Override
	public void initialize (FieldValid parameters) {
		min = parameters.min ();
		max = parameters.max ();
		isNotNull = parameters.isNotNull ();
		validateParameters ();
	}

	@Override
	public boolean isValid (CharSequence value, ConstraintValidatorContext ctx) {
		if (isNotNull == true && value == null) {
            throw new ValidationException(
                    new MethodArgumentNotValidException("value must be not null"));
		}
		int length = value.length ();
		if (length < min | length > max) {
            throw new ValidationException(
			        new MethodArgumentNotValidException("value must be between " + min + " and " + max));
		}
		return true;
	}

	private void validateParameters () {
		if (min < 0) {
			throw LOG.getMinCannotBeNegativeException ();
		}
		if (max < 0) {
			throw LOG.getMaxCannotBeNegativeException ();
		}
		if (max < min) {
			throw LOG.getLengthCannotBeNegativeException ();
		}
	}
}