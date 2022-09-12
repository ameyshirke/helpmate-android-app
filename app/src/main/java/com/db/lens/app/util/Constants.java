package com.db.lens.app.util;

public enum Constants {

    BASE_URL("https://httpbin.org");

    private String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
