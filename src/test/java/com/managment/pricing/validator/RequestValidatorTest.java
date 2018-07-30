package com.managment.pricing.validator;

import com.managment.pricing.handler.InvalidInputException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestValidatorTest {

    @Autowired
    RequestValidator requestValidator;

    @Test
    public void shouldConsiderValidateInput(){
        Double actualResponse = requestValidator.validate("123");

        Assert.assertEquals(true, 123 == actualResponse);
    }

    @Test(expected = InvalidInputException.class)
    public void shouldThrowExceptionForInvalidInput(){
       requestValidator.validate("xyz");
    }
}
