package com.example.hiringtask.model;

import com.squareup.moshi.Json;

public class Month__1 {
    @Json(name = "number")
    private Integer number;
    @Json(name = "en")
    private String en;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

}
