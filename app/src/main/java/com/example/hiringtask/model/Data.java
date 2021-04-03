package com.example.hiringtask.model;

import com.squareup.moshi.Json;

public class Data {

    @Json(name = "hijri")
    private Hijri hijri;
    @Json(name = "gregorian")
    private Gregorian gregorian;

    public Hijri getHijri() {
        return hijri;
    }

    public void setHijri(Hijri hijri) {
        this.hijri = hijri;
    }

    public Gregorian getGregorian() {
        return gregorian;
    }

    public void setGregorian(Gregorian gregorian) {
        this.gregorian = gregorian;
    }
}
