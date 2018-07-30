package com.managment.pricing.services;

import com.managment.pricing.entity.Oil;
import com.managment.pricing.entity.PriceEarningRatio;
import com.managment.pricing.entity.RevenueYield;
import com.managment.pricing.repository.PriceRepository;
import com.managment.pricing.service.PriceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceServiceTest {

    @MockBean
    PriceRepository priceRepository;

    @Autowired
    PriceService priceService;

    @Test
    public void shouldCalculateRevenueYield(){
        List<Oil> oilList = Arrays.asList(new Oil("AAC","standard", 1.0, 0.0 ,42.0),
                new Oil("REW","standard", 7.0, 0.0, 47.0));
        Mockito.when(priceRepository.findAll()).thenReturn(oilList);
        List<RevenueYield> expectedRevenueYieldsResponse = createRevenueYields();

        List<RevenueYield> revenueYieldsResponse = priceService.calculateRevenueYield(100.0);

        Assert.assertEquals(revenueYieldsResponse, expectedRevenueYieldsResponse);
    }

    @Test
    public void shouldCalculatePriceEarningRatio(){
        List<Oil> oilList = Arrays.asList(new Oil("AAC","standard", 1.0, 0.0 ,42.0),
                new Oil("REW","standard", 7.0, 0.0, 47.0));
        Mockito.when(priceRepository.findAll()).thenReturn(oilList);
        List<PriceEarningRatio> expectedRevenueYieldsResponse = createPriceEarningRatio();

        List<PriceEarningRatio> priceEarningRatioResponse = priceService.calculatePriceEarningRatio(10.0);

        Assert.assertEquals(priceEarningRatioResponse, expectedRevenueYieldsResponse);
    }


    private List<RevenueYield> createRevenueYields() {
        List<RevenueYield> revenueYields = new ArrayList<>();
        revenueYields.add(new RevenueYield("AAC",0.01));
        revenueYields.add(new RevenueYield("REW",0.07));
        return revenueYields;
    }

    private List<PriceEarningRatio> createPriceEarningRatio() {
        List<PriceEarningRatio> priceEarningRatios = new ArrayList<>();
        priceEarningRatios.add(new PriceEarningRatio("AAC",100.0));
        priceEarningRatios.add(new PriceEarningRatio("REW",14.285714285714286));
        return priceEarningRatios;
    }

}
