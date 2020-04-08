package com.airport.util.validation;

import com.airport.util.validation.impl.FieldValidValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = {FieldValidValidator.class})
public @interface FieldValid {

	String message () default "{com.airport.util.validation.FieldValid.message}";

	Class<?>[] groups () default {};

	Class<? extends Payload>[] payload () default {};

	long min () default 4L;

	long max () default 50L;

	boolean isNotNull () default true;

}
