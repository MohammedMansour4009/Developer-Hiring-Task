package com.example.hiringtask.model;

import java.util.List;


    public class Hijri {

        @com.squareup.moshi.Json(name = "date")
        private String date;
        @com.squareup.moshi.Json(name = "format")
        private String format;
        @com.squareup.moshi.Json(name = "day")
        private String day;
        @com.squareup.moshi.Json(name = "weekday")
        private Weekday weekday;
        @com.squareup.moshi.Json(name = "month")
        private Month month;
        @com.squareup.moshi.Json(name = "year")
        private String year;
        @com.squareup.moshi.Json(name = "designation")
        private Designation designation;
        @com.squareup.moshi.Json(name = "holidays")
        private List<Object> holidays = null;

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

        public Weekday getWeekday() {
            return weekday;
        }

        public void setWeekday(Weekday weekday) {
            this.weekday = weekday;
        }

        public Month getMonth() {
            return month;
        }

        public void setMonth(Month month) {
            this.month = month;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public Designation getDesignation() {
            return designation;
        }

        public void setDesignation(Designation designation) {
            this.designation = designation;
        }

        public List<Object> getHolidays() {
            return holidays;
        }

        public void setHolidays(List<Object> holidays) {
            this.holidays = holidays;
        }

}
