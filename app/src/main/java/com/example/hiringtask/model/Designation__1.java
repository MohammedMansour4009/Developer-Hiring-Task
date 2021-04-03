package com.example.hiringtask.model;

import com.squareup.moshi.Json;

public class Designation__1 {

    @Json(name = "abbreviated")
    private String abbreviated;
    @Json(name = "expanded")
    private String expanded;

    public String getAbbreviated() {
        return abbreviated;
    }

    public void setAbbreviated(String abbreviated) {
        this.abbreviated = abbreviated;
    }

    public String getExpanded() {
        return expanded;
    }

    public void setExpanded(String expanded) {
        this.expanded = expanded;
    }
}
