package com.airport.exceptions;

public class CustomException extends RuntimeException {


	public CustomException () {
		super ();
	}


	public CustomException (String customMessage) {
		super (customMessage);
	}

}
