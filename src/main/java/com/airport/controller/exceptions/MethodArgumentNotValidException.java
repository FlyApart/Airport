package com.airport.controller.exceptions;

public class MethodArgumentNotValidException extends RuntimeException {

    private static final String S_WITH_SOURCE_S_NOT_VALID = "%s with source %s not valid";
    private static final String MESSAGE_TEMPLATE = "%s not valid";

    public MethodArgumentNotValidException () {
        super();
    }

    public MethodArgumentNotValidException (String message, Throwable cause) {
        super(message, cause);
    }

    public MethodArgumentNotValidException (String entityName) {
        super(entityName);
    }

    public MethodArgumentNotValidException (String entity, String id) {
        super(String.format(S_WITH_SOURCE_S_NOT_VALID, entity, id));
    }

    public MethodArgumentNotValidException (String entity, Long id) {
        super(String.format(S_WITH_SOURCE_S_NOT_VALID, entity, id));
    }

    public MethodArgumentNotValidException (Class<?> entityClass, Object source) {
        super(String.format(S_WITH_SOURCE_S_NOT_VALID, entityClass.getSimpleName(), source));
    }
    public MethodArgumentNotValidException (Object source) {
        super(String.format(MESSAGE_TEMPLATE, source));
    }
}
