package com.example.hiringtask.model;

import com.squareup.moshi.Json;

public class RemoteConvert {
    @Json(name = "code")
    private Integer code;
    @Json(name = "status")
    private String status;
    @Json(name = "data")
    private Data data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
