package com.childhealthcare.chatapplication.data.db;

import androidx.room.TypeConverter;

import com.childhealthcare.chatapplication.model.User;
import com.google.gson.Gson;

public class DbTypeConverters {

    @TypeConverter
    public String UserToString(User user) {
        return new Gson().toJson(user);
    }

    @TypeConverter
    public User stringToUser(String string) {
        return new Gson().fromJson(string, User.class);
    }

}
