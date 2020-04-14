package com.airport.exceptions;

public class ArgumentOfMethodNotValidException extends RuntimeException {

    private static final String S_WITH_SOURCE_S_NOT_VALID = "%s with source %s not valid";
    private static final String MESSAGE_TEMPLATE = "%s not valid";


    public ArgumentOfMethodNotValidException() {
        super();
    }

    public ArgumentOfMethodNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArgumentOfMethodNotValidException(String entityName) {
        super(entityName);
    }

    public ArgumentOfMethodNotValidException(String entity, String id) {
        super(String.format(S_WITH_SOURCE_S_NOT_VALID, entity, id));
    }

    public ArgumentOfMethodNotValidException(String entity, Long id) {
        super(String.format(S_WITH_SOURCE_S_NOT_VALID, entity, id));
    }

    public ArgumentOfMethodNotValidException(Class<?> entityClass, Object source) {
        super(String.format(S_WITH_SOURCE_S_NOT_VALID, entityClass.getSimpleName(), source));
    }
    public ArgumentOfMethodNotValidException(Object source) {
        super(String.format(MESSAGE_TEMPLATE, source));
    }
}
