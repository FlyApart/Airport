package com.airline.controller;

import com.airline.controller.messages.ErrorMessage;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.jboss.logging.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class DefaultExceptionHandler {

	private static final Logger LOG = LogManager.getLogger (DefaultExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleNoSuchEntityException (MethodArgumentNotValidException e) {
		LOG.error (e.getMessage ()+"Check working handleNoSuchEntityException", e);
		return new ResponseEntity<> (new ErrorMessage (e.getMessage ()), HttpStatus.UNPROCESSABLE_ENTITY);
	}

   /* @ExceptionHandler(NoSuchEntityException)
    public ResponseEntity<ErrorMessage> handleNoSuchEntityException(NoSuchEntityException e) throws E{
        LOG.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()),
                HttpStatus.NOT_FOUND);
    }*/

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorMessage> handleAuthenticationException (AuthenticationException e) {
		LOG.error (e.getMessage (), e);
		return new ResponseEntity<> (new ErrorMessage (e.getMessage ()), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleOthersException (Exception e) {
		//Handles all other exceptions. Status code 500.
		LOG.error (e.getMessage (), e);
		return new ResponseEntity<> (new ErrorMessage (e.getMessage ()), HttpStatus.INTERNAL_SERVER_ERROR);
	}


	/*@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorMessage> getMinCannotBeNegativeException (IllegalArgumentException e) {
		//Handles all other exceptions. Status code 500.
		LOG.error (e.getMessage (), e);
		return new ResponseEntity<> (new ErrorMessage (e.getMessage ()), HttpStatus.UNPROCESSABLE_ENTITY);
	}*/


}
