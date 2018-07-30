package com.managment.pricing.service;

import com.managment.pricing.entity.Oil;
import com.managment.pricing.entity.PriceEarningRatio;
import com.managment.pricing.entity.RevenueYield;
import com.managment.pricing.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PriceService {

    @Autowired
    PriceRepository priceRepository;

    @Transactional
    public List<RevenueYield> calculateRevenueYield(Double price) {
        List<Oil> oils = priceRepository.findAll();
        Stream<Oil> oilStream = oils.stream();

        List<RevenueYield> revenueYields = oilStream.map(oil -> getRevenueYield(price, oil)).collect(Collectors.toList());

        return revenueYields;
    }

    public List<PriceEarningRatio> calculatePriceEarningRatio(double priceVal) {
        List<RevenueYield> revenueYields = calculateRevenueYield(priceVal);
        Stream<RevenueYield> revenueYieldStream = revenueYields.stream();

        List<PriceEarningRatio> priceEarningRatios = revenueYieldStream.map(revenueYield ->
                getPriceEarningRatio(priceVal, revenueYield))
                .collect(Collectors.toList());

        return priceEarningRatios;
    }

    private PriceEarningRatio getPriceEarningRatio(double priceVal, com.managment.pricing.entity.RevenueYield revenueYield) {
        return new PriceEarningRatio(revenueYield.getOilId(), (priceVal / revenueYield.getRevenueYield()));
    }

    private com.managment.pricing.entity.RevenueYield getRevenueYield(Double price, Oil oil) {

        OilRevenueYield standardReviewYield = () -> {
            double revenueYieldPrice = oil.getFixedRevenue() / price;
            return  new RevenueYield(oil.getOilId(), revenueYieldPrice);
        };

        OilRevenueYield  premiumReviewYield = () -> {
            double variableRevenuePercent = (oil.getVariableRevenue() / 100);
            double revenueYieldPrice = (variableRevenuePercent * oil.getOilBarrelValue()) / price;
            return new RevenueYield(oil.getOilId(), revenueYieldPrice);
        };

        Predicate<String> oilType = type -> oil.getType().equals("standard");

        RevenueYield revenueYield= null;
        if (oilType.test(oil.getType())) {
            revenueYield  = standardReviewYield.calculateOilRevenueYield();
        } else {
            revenueYield = premiumReviewYield.calculateOilRevenueYield();
        }
        return revenueYield;
    }


}
