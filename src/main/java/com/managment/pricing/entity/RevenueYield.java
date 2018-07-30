package com.managment.pricing.entity;

public class RevenueYield {

    private String oilId;
    private Double revenueYield;

    public RevenueYield() {
    }

    public RevenueYield(String oilId, Double revenueYield) {
        this.oilId = oilId;
        this.revenueYield = revenueYield;
    }

    public String getOilId() {
        return oilId;
    }

    public void setOilId(String oilId) {
        this.oilId = oilId;
    }

    public Double getRevenueYield() {
        return revenueYield;
    }

    public void setRevenueYield(Double revenueYield) {
        this.revenueYield = revenueYield;
    }

    @Override
    public String toString() {
        return "OilRevenueYield{" +
                "oilId='" + oilId + '\'' +
                ", revenueYield=" + revenueYield +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RevenueYield that = (RevenueYield) o;

        if (oilId != null ? !oilId.equals(that.oilId) : that.oilId != null) return false;
        return revenueYield != null ? revenueYield.equals(that.revenueYield) : that.revenueYield == null;
    }

    @Override
    public int hashCode() {
        int result = oilId != null ? oilId.hashCode() : 0;
        result = 31 * result + (revenueYield != null ? revenueYield.hashCode() : 0);
        return result;
    }
}
