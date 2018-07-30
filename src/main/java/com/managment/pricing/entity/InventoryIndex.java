package com.managment.pricing.entity;

public class InventoryIndex {

    private Integer tradeId;
    private Double geoMean;

    public InventoryIndex() {
    }

    public InventoryIndex(Integer tradeId, Double geoMean) {
        this.tradeId = tradeId;
        this.geoMean = geoMean;
    }

    public InventoryIndex(Integer tradeId) {
        this.tradeId = tradeId;
    }


    public Double getGeoMean() {
        return geoMean;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public void setGeoMean(Double geoMean) {
        this.geoMean = geoMean;
    }

    @Override
    public String toString() {
        return "InventoryIndex{" +
                "tradeId=" + tradeId +
                ", geoMean=" + geoMean +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryIndex that = (InventoryIndex) o;

        if (tradeId != null ? !tradeId.equals(that.tradeId) : that.tradeId != null) return false;
        return geoMean != null ? geoMean.equals(that.geoMean) : that.geoMean == null;
    }

    @Override
    public int hashCode() {
        int result = tradeId != null ? tradeId.hashCode() : 0;
        result = 31 * result + (geoMean != null ? geoMean.hashCode() : 0);
        return result;
    }
}
