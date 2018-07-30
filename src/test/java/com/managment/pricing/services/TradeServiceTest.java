package com.managment.pricing.services;

import com.managment.pricing.entity.*;
import com.managment.pricing.repository.PriceRepository;
import com.managment.pricing.repository.TradeRepository;
import com.managment.pricing.service.PriceService;
import com.managment.pricing.service.TradeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeServiceTest {

    @MockBean
    TradeRepository tradeRepository;

    @Autowired
    TradeService tradeService;

    @Test
    public void shouldRecordTradeTransaction(){
        when(tradeRepository.insertTradeRequest(any(TradeRequest.class))).thenReturn(123);

        Integer tradeResponseIdResponse = tradeRepository.insertTradeRequest(new TradeRequest(LocalTime.parse("18:05:32.547"), 1, 1 ,10.0));

        Assert.assertTrue("trade response id should be equal", 123 == tradeResponseIdResponse);
        verify(tradeRepository, times(1)).insertTradeRequest(any(TradeRequest.class));
    }

    @Test
    public void shouldCalculatePriceEarningRatio(){
        List<TradeResponse> tradeResponses = Arrays.asList(new TradeResponse(1,"buy" ,10.0, 1, "18:05:32.547"),
                new TradeResponse(2,"buy" ,20.0, 1, "18:20:32.547") ) ;

        Mockito.when(tradeRepository.findAll()).thenReturn(tradeResponses);

        List<TradeResponse> expectedTradeResponse = tradeService.findAllTrade();

        Assert.assertEquals(tradeResponses, expectedTradeResponse);
        verify(tradeRepository, times(1)).findAll();

    }

    @Test
    public void shouldCalculateVolumeWeightedPrice(){
        List<TradeResponse> tradeResponses = Arrays.asList(new TradeResponse(1,"buy" ,10.0, 1, "18:25:32.547"),
                new TradeResponse(2,"buy" ,20.0, 2, "18:20:32.547") ) ;
        VolumeWeightResponse volumeWeightResponse = new VolumeWeightResponse();

        Mockito.when(tradeRepository.findAll()).thenReturn(tradeResponses);

        VolumeWeightResponse expectedVolumeWeightResponse = tradeService.calculateVolumeWeightedPrice(30);

        Assert.assertEquals(volumeWeightResponse, expectedVolumeWeightResponse);
        verify(tradeRepository, times(1)).findAll();
    }

    @Test
    public void shouldCalculateInventoryIndexPrice(){
        List<TradeResponse> tradeResponses = Arrays.asList(new TradeResponse(1,"buy" ,10.0, 1, "18:25:32.547"),
                new TradeResponse(2,"buy" ,20.0, 2, "18:20:32.547") ) ;

        List<InventoryIndex> inventoryIndexes = new ArrayList<>();
        inventoryIndexes.add(new InventoryIndex(2, 14.142135623730951));

        Mockito.when(tradeRepository.findAll()).thenReturn(tradeResponses);

        List<InventoryIndex> actualInventoryIndex = tradeService.calculateInventoryIndexPrice();

        Assert.assertEquals(inventoryIndexes, actualInventoryIndex);
        verify(tradeRepository, times(1)).findAll();
    }


}
