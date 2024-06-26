package com.onur.fastproudsearch;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductApiCallback {
    private static final String BASE_URL="https://api.escuelajs.co/api/v1/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ProductApi getApiService() {
        return getClient().create(ProductApi.class);
    }
}
