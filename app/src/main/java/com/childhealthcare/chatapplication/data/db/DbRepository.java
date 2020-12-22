package com.childhealthcare.chatapplication.data.db;

import android.app.Application;

import androidx.paging.DataSource;

import com.childhealthcare.chatapplication.model.ChatMessage;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DbRepository {

    private AppDatabase db;
    private Executor executor;

    private DbRepository(Application application) {
        db = AppDatabase.getDatabase(application);
        executor = Executors.newSingleThreadExecutor();
    }

    public void addAllMessages(List<ChatMessage> messages){
        executor.execute(() -> {
            db.getMessageDao().insertAll(messages);
        });
    }

    public DataSource.Factory<Integer, ChatMessage> getChatHistory() {
        return db.getMessageDao().getAll();
    }

    private static DbRepository INSTANCE = null;

    public static DbRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new DbRepository(application);
            return INSTANCE;
        }
        return INSTANCE;
    }

}
