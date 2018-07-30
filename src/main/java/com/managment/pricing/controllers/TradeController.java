package com.managment.pricing.controllers;

import com.managment.pricing.entity.*;
import com.managment.pricing.service.TradeService;
import com.managment.pricing.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
public class TradeController {

    @Autowired
    TradeService tradeService;

    @Autowired
    RequestValidator tradeValidator;

    @PostMapping("/trade")
    public ResponseEntity<String> calculateVolumeWeightedOilPrice(@Valid @RequestBody TradeDto trade){
        TradeRequest tradeRequest = getTradeRequest(trade);
        Integer tradeTransaction = tradeService.recordTradeTransaction(tradeRequest);
        String response = "Trade Created with Id :"+tradeTransaction;
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/trade/volume-weighted")
    public VolumeWeightResponse calculateVolumeWeightedPrice(@RequestParam("timeInMin") Optional<String> timeInMin){

        String defaultTime = timeInMin.orElse("30");
        int time = tradeValidator.validate(defaultTime).intValue();

        VolumeWeightResponse volumeWeightResponse = tradeService.calculateVolumeWeightedPrice(time);
        return volumeWeightResponse;
    }

    @GetMapping("/trade/inventory-index")
    public List<InventoryIndex> calculateInventoryIndexPrice(){
        List<InventoryIndex> inventoryIndexes = tradeService.calculateInventoryIndexPrice();
        return inventoryIndexes;
    }


    @GetMapping("/trade/view")
    public List<TradeResponse> showTrades(){
        List<TradeResponse> tradeResponses = tradeService.findAllTrade();
        return tradeResponses;
    }

    private TradeRequest getTradeRequest(TradeDto trade) {
        double priceVal = Double.valueOf(trade.getPrice());
        int quantityVal = Integer.parseInt(trade.getQuantity());
        int buySellIndicator =  getBySellIndicator(trade.getBuySellIndicator());
        return new TradeRequest(LocalTime.now(), quantityVal, buySellIndicator, priceVal);
    }

    private int getBySellIndicator(String tradeIndicator){
        return tradeIndicator.equals("buy")? 1 : 0;
    }
}
