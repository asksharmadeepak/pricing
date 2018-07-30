package com.managment.pricing.controllers;

import com.managment.pricing.entity.*;
import com.managment.pricing.service.PriceService;
import com.managment.pricing.service.TradeService;
import com.managment.pricing.validator.RequestValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeService tradeService;

    @MockBean
    private RequestValidator requestValidator;


    @Test
    public void shouldCalculateRevenueYieldForPrice() throws Exception {
        List<InventoryIndex> inventoryIndexes = Arrays.asList(new InventoryIndex(1, 10.0),new InventoryIndex(2, 30.0) ) ;

        Mockito.when(tradeService.calculateInventoryIndexPrice()).thenReturn(inventoryIndexes);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/trade/inventory-index");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"tradeId\":1,\"geoMean\":10.0},{\"tradeId\":2,\"geoMean\":30.0}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void shouldCalculateVolumeWeightedPrice() throws Exception {
        VolumeWeightResponse volumeWeightResponse = new VolumeWeightResponse(LocalTime.parse("18:05:32.547"), 100.0);

        Mockito.when(tradeService.calculateVolumeWeightedPrice(Mockito.anyInt())).thenReturn(volumeWeightResponse);
        Mockito.when(requestValidator.validate("100")).thenReturn(100.0);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/trade/volume-weighted");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"time\":\"18:05:32.547\",\"volumeWeightedPrice\":100.0}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void shouldCalculateInventoryIndexPrice() throws Exception {
        List<InventoryIndex> inventoryIndexes = Arrays.asList(new InventoryIndex(1, 10.0),new InventoryIndex(2, 20.0) ) ;
        Mockito.when(tradeService.calculateInventoryIndexPrice()).thenReturn(inventoryIndexes);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/trade/inventory-index");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"tradeId\":1,\"geoMean\":10.0},{\"tradeId\":2,\"geoMean\":20.0}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void shouldShowTrades() throws Exception {
        List<TradeResponse> tradeResponses = Arrays.asList(new TradeResponse(1,"buy" ,10.0, 1, "18:05:32.547"),
                    new TradeResponse(2,"buy" ,20.0, 1, "18:20:32.547") ) ;

        Mockito.when(tradeService.findAllTrade()).thenReturn(tradeResponses);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/trade/view");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"tradeId\":1,\"tradeStatus\":\"buy\",\"tradePrice\":10.0,\"quantity\":1,\"tradeTime\":\"18:05:32.547\"},{\"tradeId\":2,\"tradeStatus\":\"buy\",\"tradePrice\":20.0,\"quantity\":1,\"tradeTime\":\"18:20:32.547\"}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}
