package com.airline.util.exceptions;

public class NoSuchEntityException extends RuntimeException {

    public NoSuchEntityException (){
        super();
    }

    public NoSuchEntityException(String message){
        super(message);
    }

    public NoSuchEntityException (String message, Exception exception){
        super(message,exception);
    }
}
