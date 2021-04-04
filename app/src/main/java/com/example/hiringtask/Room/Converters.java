package com.example.hiringtask.Room;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class Converters {
    @TypeConverter
    public   String fromUserToGosn(User user) {
        return new Gson().toJson(user);
    }

    @TypeConverter
    public   User fromGosnToUser(String stringUser) {
        return new Gson().fromJson(stringUser, User.class);
    }

}

//class RowDataConverter {
//    @TypeConverter
//    public static String fromUserToGosn(User user) {
//        return new Gson().toJson(user);
//    }
//
//    @TypeConverter
//    public static User fromGosnToUser(String stringUser) {
//        return new Gson().fromJson(stringUser, User.class);
//    }
//}