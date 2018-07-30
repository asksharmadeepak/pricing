package com.managment.pricing.validator;

import com.managment.pricing.handler.InvalidInputException;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator {

    public Double validate(String inputValue) {
        double priceVal;
        if (inputValue.matches("[0-9]+")){
            priceVal = Double.valueOf(inputValue);
        }else{
            throw new InvalidInputException("Invalid Input Only numbers are allowed");
        }
        return priceVal;
    }
}
