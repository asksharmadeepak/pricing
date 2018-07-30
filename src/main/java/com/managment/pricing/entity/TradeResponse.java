package com.managment.pricing.entity;

public class TradeResponse {

    private Integer tradeId;
    private String tradeStatus;
    private Double tradePrice;
    private Integer quantity;
    private String tradeTime;


    public TradeResponse() {
    }

    public TradeResponse(Integer tradeId, String tradeStatus, Double tradePrice, Integer quantity, String tradeTime) {
        this.tradeId = tradeId;
        this.tradeStatus = tradeStatus;
        this.tradePrice = tradePrice;
        this.quantity = quantity;
        this.tradeTime = tradeTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public TradeResponse(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(Double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus.equals("1")? "buy" : "sell";
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }
}
