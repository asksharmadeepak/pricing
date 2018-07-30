package com.managment.pricing.handler;


public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String customMessage) {
        super(customMessage);
    }

}
