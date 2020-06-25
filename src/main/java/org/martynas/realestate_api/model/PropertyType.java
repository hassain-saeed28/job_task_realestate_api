package org.martynas.realestate_api.model;

public enum PropertyType {
    APARTMENT("A"),
    HOUSE("H"),
    INDUSTRIAL("I");

    private String code;

    private PropertyType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}