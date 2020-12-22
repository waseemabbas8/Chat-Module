package com.childhealthcare.chatapplication.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.childhealthcare.chatapplication.model.ChatMessage;

import kotlin.jvm.Volatile;

@Database(entities = {ChatMessage.class}, version = 1)
@TypeConverters(DbTypeConverters.class)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MessageDao getMessageDao();

    @Volatile
    private static AppDatabase INSTANCE = null;
    private static final String DATABASE_NAME = "waseem_chat.db";

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
            return INSTANCE;
        }
        return INSTANCE;
    }
}
