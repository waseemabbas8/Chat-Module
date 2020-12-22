package com.childhealthcare.chatapplication.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiClientFactory {
    private static final String API_BASE_URL = "http://165.22.183.7/api/";

    private static ApiClient INSTANCE = null;

    private ApiClientFactory() {}

    public static ApiClient create() {
        if (INSTANCE == null) {
            INSTANCE = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiClient.class);
            return INSTANCE;
        }
        return INSTANCE;
    }

}
