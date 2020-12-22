package com.childhealthcare.chatapplication.data.remote;

import com.childhealthcare.chatapplication.model.BaseResponse;
import com.childhealthcare.chatapplication.model.ChatMessage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiClient {

    @GET("get-messages/{id}")
    Call<BaseResponse<List<ChatMessage>>> getChatMessages(
            @Header("Authorization") String authorization,
            @Path("id") int id
    );



}
