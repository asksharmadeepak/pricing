package com.managment.pricing.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(InvalidInputException.class)
    public ErrorResponse handleNotFoundException(InvalidInputException invalidInputException) {
        return new ErrorResponse("Error is: "+invalidInputException.getMessage());
    }
}
