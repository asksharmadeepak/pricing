package com.managment.pricing.entity;

import java.time.LocalTime;

public class VolumeWeightResponse {

    private LocalTime time;
    private Double volumeWeightedPrice;

    public VolumeWeightResponse() {
    }

    public VolumeWeightResponse(LocalTime time, Double volumeWeightedPrice) {
        this.time = time;
        this.volumeWeightedPrice = volumeWeightedPrice;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Double getVolumeWeightedPrice() {
        return volumeWeightedPrice;
    }

    public void setVolumeWeightedPrice(Double volumeWeightedPrice) {
        this.volumeWeightedPrice = volumeWeightedPrice;
    }

    @Override
    public String toString() {
        return "VolumeWeightResponse{" +
                "time=" + time +
                ", volumeWeightedPrice=" + volumeWeightedPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VolumeWeightResponse that = (VolumeWeightResponse) o;

        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        return volumeWeightedPrice != null ? volumeWeightedPrice.equals(that.volumeWeightedPrice) : that.volumeWeightedPrice == null;
    }

    @Override
    public int hashCode() {
        int result = time != null ? time.hashCode() : 0;
        result = 31 * result + (volumeWeightedPrice != null ? volumeWeightedPrice.hashCode() : 0);
        return result;
    }
}
