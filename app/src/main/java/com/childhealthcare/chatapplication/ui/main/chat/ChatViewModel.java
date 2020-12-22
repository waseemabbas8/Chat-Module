package com.childhealthcare.chatapplication.ui.main.chat;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.childhealthcare.chatapplication.data.db.DbRepository;
import com.childhealthcare.chatapplication.data.remote.RemoteRepository;
import com.childhealthcare.chatapplication.data.remote.chat.ChatBoundaryCallback;
import com.childhealthcare.chatapplication.model.ChatMessage;

public class ChatViewModel extends AndroidViewModel {

    public ChatViewModel(@NonNull Application application) {
        super(application);

        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(20)
                .setPrefetchDistance(60)
                .setEnablePlaceholders(false)
                .build();
        DataSource.Factory<Integer, ChatMessage> factory = dbRepository.getChatHistory();

        messages = new LivePagedListBuilder(factory, config)
                .setBoundaryCallback(new ChatBoundaryCallback(dbRepository, remoteRepository))
                .build();

    }

    private RemoteRepository remoteRepository = RemoteRepository.getInstance();
    private DbRepository dbRepository = DbRepository.getInstance(getApplication());

    public LiveData<PagedList<ChatMessage>> messages;

}
