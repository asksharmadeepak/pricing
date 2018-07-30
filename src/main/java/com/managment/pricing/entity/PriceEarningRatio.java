package com.managment.pricing.entity;

public class PriceEarningRatio {

    private String oilId;
    private Double priceEarningRatio;

    public PriceEarningRatio() {
    }

    public PriceEarningRatio(String oilId, Double priceEarningRatio) {
        this.oilId = oilId;
        this.priceEarningRatio = priceEarningRatio;
    }

    public String getOilId() {
        return oilId;
    }

    public void setOilId(String oilId) {
        this.oilId = oilId;
    }

    public Double getPriceEarningRatio() {
        return priceEarningRatio;
    }

    public void setPriceEarningRatio(Double priceEarningRatio) {
        this.priceEarningRatio = priceEarningRatio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceEarningRatio that = (PriceEarningRatio) o;

        if (oilId != null ? !oilId.equals(that.oilId) : that.oilId != null) return false;
        return priceEarningRatio != null ? priceEarningRatio.equals(that.priceEarningRatio) : that.priceEarningRatio == null;
    }

    @Override
    public int hashCode() {
        int result = oilId != null ? oilId.hashCode() : 0;
        result = 31 * result + (priceEarningRatio != null ? priceEarningRatio.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PriceEarningRatio{" +
                "oilId='" + oilId + '\'' +
                ", priceEarningRatio=" + priceEarningRatio +
                '}';
    }
}
