package com.airport.exceptions;

import com.airport.exceptions.messages.ErrorMessage;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
@Slf4j
public class DefaultExceptionHandler extends DefaultResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleEntityNotFoundException (EntityNotFoundException e) {
		log.error (e.getMessage (), e);
		return new ResponseEntity<> (new ErrorMessage (e.getMessage ()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EntityAlreadyExistException.class)
	public ResponseEntity<ErrorMessage> handleEntityAlreadyExistException (EntityAlreadyExistException e) {
		log.error (e.getMessage (), e);
		return new ResponseEntity<> (new ErrorMessage (e.getMessage ()), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorMessage> handleAuthenticationException (AuthenticationException e) {
		log.error (e.getMessage (), e);
		return new ResponseEntity<> (new ErrorMessage (e.getMessage ()), HttpStatus.UNAUTHORIZED);
	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleException (Exception e) {
		log.error (e.getMessage (), e);
		return new ResponseEntity<> (new ErrorMessage (e.getMessage ()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ArgumentOfMethodNotValidException.class, NumberFormatException.class, ConversionException.class})
	public ResponseEntity<ErrorMessage> handleArgumentOfMethodNotValidException (RuntimeException e) {
		log.error (e.getMessage (), e);
		return new ResponseEntity<> (new ErrorMessage (e.getMessage ()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorMessage> handleCustomException (CustomException e) {
		log.error (e.getMessage (), e);
		return new ResponseEntity<> (new ErrorMessage (e.getMessage ()), HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler({UnsupportedJwtException.class, MalformedJwtException.class, SignatureException.class, ExpiredJwtException.class, IllegalArgumentException.class})
	public ResponseEntity<ErrorMessage> handleParseClaimsException (Exception e) {
		log.error (e.getMessage (), e);
		return new ResponseEntity<> (new ErrorMessage (e + e.getMessage ()), HttpStatus.UNAUTHORIZED);
	}

}
