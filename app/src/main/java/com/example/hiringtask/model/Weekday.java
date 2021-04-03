package com.example.hiringtask.model;

import com.squareup.moshi.Json;

public class Weekday {

    @Json(name = "en")
    private String en;
    @Json(name = "ar")
    private String ar;

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
