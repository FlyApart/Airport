package com.airport.util.validation;

import com.airport.util.validation.impl.FieldValidValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = {FieldValidValidator.class})
public @interface FieldValid {

	String message () default "Checking field for length and null";//TODO fix log

	Class<?>[] groups () default {};

	Class<? extends Payload>[] payload () default {};

	int min () default 4;

	int max () default 50;

	boolean isNotNull () default true;


}
