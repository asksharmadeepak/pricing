package com.managment.pricing.handler;

public class ErrorResponse {

    private String errorDetail;

    public ErrorResponse(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
}
