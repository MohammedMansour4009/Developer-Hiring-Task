package com.example.hiringtask.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "post_table")
public class Events {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id ;
    @ColumnInfo(name = "userId")
    private User userId;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "gregorianDate")
    private String gregorianDate;
    @ColumnInfo(name = "hijriDate")
    private String hijriDate;
    @ColumnInfo(name = "serverDatetime")
    private String serverDatetime;

    public Events() {
    }

    public Events(User userId, String name, String description, String gregorianDate, String hijriDate, String serverDatetime) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.gregorianDate = gregorianDate;
        this.hijriDate = hijriDate;
        this.serverDatetime = serverDatetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGregorianDate() {
        return gregorianDate;
    }

    public void setGregorianDate(String gregorianDate) {
        this.gregorianDate = gregorianDate;
    }

    public String getHijriDate() {
        return hijriDate;
    }

    public void setHijriDate(String hijriDate) {
        this.hijriDate = hijriDate;
    }

    public String getServerDatetime() {
        return serverDatetime;
    }

    public void setServerDatetime(String serverDatetime) {
        this.serverDatetime = serverDatetime;
    }
}
