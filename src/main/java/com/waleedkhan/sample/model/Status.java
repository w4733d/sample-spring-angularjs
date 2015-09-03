package com.waleedkhan.sample.model;

public enum Status implements HasCode {
    APPROVED("APPROVED"),
    PENDING("PENDING"),
    REJECTED("REJECTED");

    private final String code;

    Status(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
