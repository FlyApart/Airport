package com.airport.controller.exceptions;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;

public class ConversionException extends ConversionFailedException {

	public ConversionException (TypeDescriptor sourceType, TypeDescriptor targetType, Object value, Throwable cause) {
		super (sourceType, targetType, value, cause);
	}
	public ConversionException (Class<?>  sourceClass, Class<?>  targetClass, Object value, Throwable cause) {
		super (TypeDescriptor.valueOf(sourceClass), TypeDescriptor.valueOf(targetClass), value, cause);
	}
}
