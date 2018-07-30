package com.managment.pricing.service;

import com.managment.pricing.entity.*;
import com.managment.pricing.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TradeService {

    @Autowired
    TradeRepository tradeRepository;

    @Transactional
        public Integer recordTradeTransaction(TradeRequest tradeRequest) {
        Integer tradeResponseId = tradeRepository.insertTradeRequest(tradeRequest);
        return tradeResponseId;
    }

    @Transactional
    public List<TradeResponse> findAllTrade() {
        return tradeRepository.findAll();
    }

    public VolumeWeightResponse calculateVolumeWeightedPrice(int time) {

        List<TradeResponse> tradeResponses = findAllTrade();
        LocalTime thirtyMinFromNow = LocalTime.now().minusMinutes(time);
        Stream<TradeResponse> tradeResponseStream = tradeResponses.stream();

        List<VolumeWeight> volumeWeightList = tradeResponseStream
                .map(tradeResponse -> getVolumeWeight(thirtyMinFromNow, tradeResponse)).collect(Collectors.toList());
        VolumeWeightResponse volumeWeightResponse = null;
        if(!isAllNull(volumeWeightList)){
            volumeWeightResponse = getVolumeWeightResponse(volumeWeightList);
        }else{
             volumeWeightResponse = new VolumeWeightResponse();
        }
        return volumeWeightResponse;
    }

    public List<InventoryIndex> calculateInventoryIndexPrice() {
        List<TradeResponse> tradeResponses = findAllTrade();
        double product = 1.0;
        int dataLength = 0;
        InventoryIndex inventoryIndex = new InventoryIndex();
        for (TradeResponse tradeResponse: tradeResponses) {
            product = product * tradeResponse.getTradePrice();
            dataLength++;
            inventoryIndex.setTradeId(tradeResponse.getTradeId());
        }
        double geoMean = Math.pow(product, 1.0 / dataLength);
        inventoryIndex.setGeoMean(geoMean);
        List<InventoryIndex> inventoryIndexList = new ArrayList<>();
        inventoryIndexList.add(inventoryIndex);

        return inventoryIndexList;

    }

    private VolumeWeight getVolumeWeight(LocalTime thirtyMinBeforeNow, TradeResponse tradeResponse) {
        VolumeWeight volumeWeight = null;
        String responseTime = tradeResponse.getTradeTime();
        if (LocalTime.parse(responseTime).isAfter(thirtyMinBeforeNow) &&
                LocalTime.parse(responseTime).isBefore(thirtyMinBeforeNow.plusMinutes(30))) {
            volumeWeight = new VolumeWeight();
            volumeWeight.setTradeId(tradeResponse.getTradeId());
            volumeWeight.setTradeTime(responseTime);
            volumeWeight.setPrice(tradeResponse.getTradePrice());
            volumeWeight.setQuantity(tradeResponse.getQuantity());
        }
        return volumeWeight;
    }

    private VolumeWeightResponse getVolumeWeightResponse(List<VolumeWeight> volumeWeightList) {
        double priceSummation = 0.0;
        int quantitySummation = 0;

        for (VolumeWeight volumeWeight :volumeWeightList) {
            Double price = volumeWeight.getPrice();
            Integer quantity = volumeWeight.getQuantity();

            priceSummation +=  (price * quantity);
            quantitySummation += quantity;
        }

        double volumeWeightedPrice = (priceSummation / quantitySummation);

        VolumeWeightResponse volumeWeightResponse = new VolumeWeightResponse();
        volumeWeightResponse.setTime(LocalTime.now());
        volumeWeightResponse.setVolumeWeightedPrice(volumeWeightedPrice);
        return volumeWeightResponse;
    }

    private boolean isAllNull(Iterable<?> list){
        for(Object obj : list){
            if(obj != null)
                return false;
        }
        return true;
    }
}