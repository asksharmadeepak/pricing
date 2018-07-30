package com.managment.pricing.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class TradeDto {

    @NotNull
    @Pattern(regexp="[0-9]")
    private String quantity;
    @NotNull
    @Pattern(regexp="(buy|sell)")
    private String buySellIndicator;
    @NotNull
    @Pattern(regexp="[0-9]+")
    private String price;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getBuySellIndicator() {
        return buySellIndicator;
    }

    public void setBuySellIndicator(String buySellIndicator) {
        this.buySellIndicator = buySellIndicator;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
