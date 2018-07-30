package com.managment.pricing.entity;


public class Oil {

    private String oilId;
    private String type;
    private Double fixedRevenue;
    private Double variableRevenue;
    private Double oilBarrelValue;

    public Oil() {
    }

    public Oil(String oilId, String type, Double fixedRevenue, Double variableRevenue, Double oilBarrelValue) {
        this.oilId = oilId;
        this.type = type;
        this.fixedRevenue = fixedRevenue;
        this.variableRevenue = variableRevenue;
        this.oilBarrelValue = oilBarrelValue;
    }

    public String getOilId() {
        return oilId;
    }

    public void setOilId(String oilId) {
        this.oilId = oilId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getFixedRevenue() {
        return fixedRevenue;
    }

    public void setFixedRevenue(Double fixedRevenue) {
        this.fixedRevenue = fixedRevenue;
    }

    public Double getVariableRevenue() {
        return variableRevenue;
    }

    public void setVariableRevenue(Double variableRevenue) {
        this.variableRevenue = variableRevenue;
    }

    public Double getOilBarrelValue() {
        return oilBarrelValue;
    }

    public void setOilBarrelValue(Double oilBarrelValue) {
        this.oilBarrelValue = oilBarrelValue;
    }
}

