package com.airport.controller.exceptions;

public class MethodArgumentNotValidException extends RuntimeException {

    private static final String MESSAGE_ID_TEMPLATE = "%s with source %s not valid";
    private static final String MESSAGE_TEMPLATE = "%s  sources not valid";

    public MethodArgumentNotValidException () {
        super();
    }

    public MethodArgumentNotValidException (String message, Throwable cause) {
        super(message, cause);
    }

    public MethodArgumentNotValidException (String entityName) {
        super(String.format(MESSAGE_TEMPLATE, entityName));
    }

    public MethodArgumentNotValidException (String entity, String id) {
        super(String.format(MESSAGE_ID_TEMPLATE, entity, id));
    }

    public MethodArgumentNotValidException (String entity, Long id) {
        super(String.format(MESSAGE_ID_TEMPLATE, entity, id));
    }

    public MethodArgumentNotValidException (Class<?> entityClass, Object source) {
        super(String.format(MESSAGE_ID_TEMPLATE, entityClass.getSimpleName(), source));
    }
    public MethodArgumentNotValidException (Object source) {
        super(String.format(MESSAGE_TEMPLATE, source));
    }
}
