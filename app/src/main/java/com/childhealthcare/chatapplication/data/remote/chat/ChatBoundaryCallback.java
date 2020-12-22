package com.childhealthcare.chatapplication.data.remote.chat;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.childhealthcare.chatapplication.data.db.DbRepository;
import com.childhealthcare.chatapplication.data.remote.ApiClient;
import com.childhealthcare.chatapplication.data.remote.RemoteRepository;
import com.childhealthcare.chatapplication.model.BaseResponse;
import com.childhealthcare.chatapplication.model.ChatMessage;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.childhealthcare.chatapplication.data.Constants.TOKEN;


public class ChatBoundaryCallback extends PagedList.BoundaryCallback<ChatMessage> {

    private DbRepository dbRepository;
    private RemoteRepository remoteRepository;

    private boolean hasLoadedAllItems = false;
    // avoid triggering multiple requests in the same time
    private boolean isRequestInProgress = false;

    public ChatBoundaryCallback(DbRepository dbRepository, RemoteRepository remoteRepository) {
        this.dbRepository = dbRepository;
        this.remoteRepository = remoteRepository;
    }

    @Override
    public void onZeroItemsLoaded() {
        loadChat();
    }

    @Override
    public void onItemAtEndLoaded(@NonNull ChatMessage itemAtEnd) {
        loadChat();
    }

    private void loadChat() {
        if (isRequestInProgress || hasLoadedAllItems) return;

        isRequestInProgress = true;

        Call<BaseResponse<List<ChatMessage>>> call = remoteRepository.getChatMessages(TOKEN, 1);
        call.enqueue(new Callback<BaseResponse<List<ChatMessage>>>() {

            @Override
            public void onResponse(Call<BaseResponse<List<ChatMessage>>> call, Response<BaseResponse<List<ChatMessage>>> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            List<ChatMessage> items = response.body().getData();
                            if (items.isEmpty()) {
                                hasLoadedAllItems = true;
                            } else {
                                dbRepository.addAllMessages(items);
                            }

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                isRequestInProgress = false;
            }

            @Override
            public void onFailure(Call<BaseResponse<List<ChatMessage>>> call, Throwable t) {
                isRequestInProgress = false;
            }
        });
    }
}
