package com.childhealthcare.chatapplication.data.remote;


import com.childhealthcare.chatapplication.model.BaseResponse;
import com.childhealthcare.chatapplication.model.ChatMessage;

import java.util.List;

import retrofit2.Call;

public final class RemoteRepository {

    private ApiClient apiClient;

    private RemoteRepository(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Call<BaseResponse<List<ChatMessage>>> getChatMessages(String authorization, int id) {
        return apiClient.getChatMessages("Bearer " + authorization, id);
    }

    private static RemoteRepository INSTANCE = null;

    public static RemoteRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(ApiClientFactory.create());
            return INSTANCE;
        }
        return INSTANCE;
    }

}
