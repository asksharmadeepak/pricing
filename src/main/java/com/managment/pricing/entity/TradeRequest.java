package com.managment.pricing.entity;

import java.time.LocalTime;


public class TradeRequest {

    private Integer transactionId;
    private LocalTime timestamp;
    private Integer quantity;
    private Integer buySellIndicator;
    private Double price;

    public TradeRequest() {
    }

    public TradeRequest(LocalTime timestamp, Integer quantity, Integer buySellIndicator, Double price) {
        this.timestamp = timestamp;
        this.quantity = quantity;
        this.buySellIndicator = buySellIndicator;
        this.price = price;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getBuySellIndicator() {
        return buySellIndicator;
    }

    public void setBuySellIndicator(Integer buySellIndicator) {
        this.buySellIndicator = buySellIndicator;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
