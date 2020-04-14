package com.airport.exceptions.messages;

public class CreationMessage {
	private String successMessage;

	public CreationMessage (String message) {
		this.successMessage = message;
	}

	public String getMessage () {
		return successMessage;
	}
}
