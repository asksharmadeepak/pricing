package com.managment.pricing.entity;

public class VolumeWeight {

    private Integer tradeId;
    private String tradeTime;
    private Integer quantity;
    private Double price;
    private Double volumeWeight;

    public VolumeWeight() {
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getVolumeWeight() {
        return volumeWeight;
    }

    public void setVolumeWeight(Double volumeWeight) {
        this.volumeWeight = volumeWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VolumeWeight that = (VolumeWeight) o;

        if (tradeId != null ? !tradeId.equals(that.tradeId) : that.tradeId != null) return false;
        if (tradeTime != null ? !tradeTime.equals(that.tradeTime) : that.tradeTime != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return volumeWeight != null ? volumeWeight.equals(that.volumeWeight) : that.volumeWeight == null;
    }

    @Override
    public int hashCode() {
        int result = tradeId != null ? tradeId.hashCode() : 0;
        result = 31 * result + (tradeTime != null ? tradeTime.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (volumeWeight != null ? volumeWeight.hashCode() : 0);
        return result;
    }
}
