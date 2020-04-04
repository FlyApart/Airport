package com.airline.util.exceptions;

public class EntityAlreadyExist extends RuntimeException {

    private static final String MESSAGE_ID_TEMPLATE = "%s with source %s already exist";
    private static final String MESSAGE_TEMPLATE = "%s already exist";

    public EntityAlreadyExist () {
        super();
    }

    public EntityAlreadyExist (String message, Throwable cause) {
        super(message, cause);
    }

    public EntityAlreadyExist (String entityName) {
        super(String.format(MESSAGE_TEMPLATE, entityName));
    }

    public EntityAlreadyExist (String entity, String id) {
        super(String.format(MESSAGE_ID_TEMPLATE, entity, id));
    }

    public EntityAlreadyExist (String entity, Long id) {
        super(String.format(MESSAGE_ID_TEMPLATE, entity, id));
    }

    public EntityAlreadyExist (Class<?> entityClass, Object source) {
        super(String.format(MESSAGE_ID_TEMPLATE, entityClass.getSimpleName(), source));
    }
}
