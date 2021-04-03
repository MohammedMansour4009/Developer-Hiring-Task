package com.example.hiringtask.model;

import com.squareup.moshi.Json;

public class Month {

    @Json(name = "number")
    private Integer number;
    @Json(name = "en")
    private String en;
    @Json(name = "ar")
    private String ar;

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

    public String getAr() {
        return ar;
    }

    public void setAr(String ar) {
        this.ar = ar;
    }
}
