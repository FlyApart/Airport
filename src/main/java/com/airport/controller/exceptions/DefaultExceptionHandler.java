package com.airport.controller.exceptions;

import com.airport.controller.messages.ErrorMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;



@ControllerAdvice
public class DefaultExceptionHandler extends DefaultResponseEntityExceptionHandler {
    private static final Logger LOG = LogManager.getLogger (DefaultExceptionHandler.class);

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException e){
		LOG.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(e.getMessage()),
				HttpStatus.NOT_FOUND);
	}

    @ExceptionHandler(EntityAlreadyExistException.class)
	public ResponseEntity<ErrorMessage> handleEntityAlreadyExistException (EntityAlreadyExistException e) {
		LOG.error (e.getMessage (), e);
		return new ResponseEntity<> (new ErrorMessage (e.getMessage ()), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ConversionException.class)
	public ResponseEntity<ErrorMessage> handleConversionFailedException (ConversionException e) {
		LOG.error (e.getMessage (), e);
		return new ResponseEntity<> (new ErrorMessage (e.getMessage ()), HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorMessage> handleAuthenticationException (AuthenticationException e) {
		LOG.error (e.getMessage (), e);
		return new ResponseEntity<> (new ErrorMessage (e.getMessage ()), HttpStatus.UNAUTHORIZED);
	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleException (Exception e) {
		LOG.error (e.getMessage (), e);
		return new ResponseEntity<> (new ErrorMessage (e.getMessage ()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ArgumentOfMethodNotValidException.class)
	public ResponseEntity<ErrorMessage> handleArgumentOfMethodNotValidException (ArgumentOfMethodNotValidException e) {
		LOG.error (e.getMessage (), e);
		return new ResponseEntity<> (new ErrorMessage (e.getMessage ()+e),HttpStatus.BAD_REQUEST);
	}

}
