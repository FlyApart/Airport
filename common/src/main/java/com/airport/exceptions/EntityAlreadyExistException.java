package com.airport.exceptions;

public class EntityAlreadyExistException extends RuntimeException {

    private static final String MESSAGE_ID_TEMPLATE = "%s with source %s already exist";
    private static final String MESSAGE_TEMPLATE = "%s already exist";

    public EntityAlreadyExistException() {
        super();
    }

    public EntityAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityAlreadyExistException(String entityName) {
        super(String.format(MESSAGE_TEMPLATE, entityName));
    }

    public EntityAlreadyExistException(String entity, String id) {
        super(String.format(MESSAGE_ID_TEMPLATE, entity, id));
    }

    public EntityAlreadyExistException(String entity, Long id) {
        super(String.format(MESSAGE_ID_TEMPLATE, entity, id));
    }

    public EntityAlreadyExistException(Class<?> entityClass, Object source) {
        super(String.format(MESSAGE_ID_TEMPLATE, entityClass.getSimpleName(), source));
    }
}
