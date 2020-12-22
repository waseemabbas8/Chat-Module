package com.childhealthcare.chatapplication.data.db;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.childhealthcare.chatapplication.model.ChatMessage;

import java.util.List;

@Dao
public interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ChatMessage chatMessage);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ChatMessage> chatMessages);

    @Query("SELECT * FROM message ORDER BY id DESC")
    DataSource.Factory<Integer, ChatMessage> getAll();

}
