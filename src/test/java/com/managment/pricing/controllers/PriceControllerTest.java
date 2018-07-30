package com.managment.pricing.controllers;

import com.managment.pricing.entity.PriceEarningRatio;
import com.managment.pricing.entity.RevenueYield;
import com.managment.pricing.service.PriceService;
import com.managment.pricing.validator.RequestValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    @MockBean
    private RequestValidator requestValidator;


    @Test
    public void shouldCalculateRevenueYieldForPrice() throws Exception {
        List<RevenueYield> revenueYields = Arrays.asList(new RevenueYield("AEW", 10.0),new RevenueYield("CEW", 20.0) ) ;

        Mockito.when(priceService.calculateRevenueYield(Mockito.anyDouble())).thenReturn(revenueYields);
        Mockito.when(requestValidator.validate("200")).thenReturn(200.0);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/price/revenue-yield/price");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"oilId\":\"AEW\",\"revenueYield\":10.0},{\"oilId\":\"CEW\",\"revenueYield\":20.0}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void shouldCalculateEarningRatioPrice() throws Exception {
        List<PriceEarningRatio> priceEarningRatios = Arrays.asList(new PriceEarningRatio("AEW", 10.0),new PriceEarningRatio("CEW", 20.0) ) ;

        Mockito.when(priceService.calculatePriceEarningRatio(Mockito.anyDouble())).thenReturn(priceEarningRatios);
        Mockito.when(requestValidator.validate("100")).thenReturn(100.0);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/price/earning-ratio/price");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"oilId\":\"AEW\",\"priceEarningRatio\":10.0},{\"oilId\":\"CEW\",\"priceEarningRatio\":20.0}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}
