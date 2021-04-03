package com.example.hiringtask.model;

import com.squareup.moshi.Json;


public class Gregorian {

    @Json(name = "date")
    private String date;
    @Json(name = "format")
    private String format;
    @Json(name = "day")
    private String day;
    @Json(name = "weekday")
    private Weekday__1 weekday;
    @Json(name = "month")
    private Month__1 month;
    @Json(name = "year")
    private String year;
    @Json(name = "designation")
    private Designation__1 designation;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Weekday__1 getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday__1 weekday) {
        this.weekday = weekday;
    }

    public Month__1 getMonth() {
        return month;
    }

    public void setMonth(Month__1 month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Designation__1 getDesignation() {
        return designation;
    }

    public void setDesignation(Designation__1 designation) {
        this.designation = designation;
    }

}

