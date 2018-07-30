package com.managment.pricing.controllers;

import com.managment.pricing.entity.PriceEarningRatio;
import com.managment.pricing.entity.RevenueYield;
import com.managment.pricing.handler.InvalidInputException;
import com.managment.pricing.service.PriceService;
import com.managment.pricing.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PriceController {

    @Autowired
    PriceService priceService;

    @Autowired
    RequestValidator priceValidator;

    @GetMapping("/price/revenue-yield/{price}")
    public List<RevenueYield> calculateRevenueYieldForPrice(@PathVariable(value="price") String price) throws InvalidInputException {
        double validPrice = priceValidator.validate(price);
        List<RevenueYield> revenueYields = priceService.calculateRevenueYield(validPrice);
        return revenueYields;
    }

    @GetMapping("/price/earning-ratio/{price}")
    public List<PriceEarningRatio> calculateEarningRatioPrice(@PathVariable(value="price") String price){
        double validPrice = priceValidator.validate(price);
        List<PriceEarningRatio> revenueYields = priceService.calculatePriceEarningRatio(validPrice);
        return revenueYields;
    }
}
