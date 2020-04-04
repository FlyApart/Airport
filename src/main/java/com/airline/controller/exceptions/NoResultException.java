package com.airline.controller.exceptions;

import javax.persistence.PersistenceException;

public class NoResultException extends PersistenceException {

    private static final String MESSAGE_ID_TEMPLATE = "%s with id= %s not found";
    private static final String MESSAGE_TEMPLATE = "%s not found";

    public NoResultException() {
        super();
    }

    public NoResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoResultException(String entityName) {
        super(String.format(MESSAGE_TEMPLATE, entityName));
    }

    public NoResultException(String entity, String id) {
        super(String.format(MESSAGE_ID_TEMPLATE, entity, id));
    }

    public NoResultException(String entity, Long id) {
        super(String.format(MESSAGE_ID_TEMPLATE, entity, id));
    }

    public NoResultException(Class<?> entityClass, Object id) {
        super(String.format(MESSAGE_ID_TEMPLATE, entityClass.getSimpleName(), id));
    }

}
