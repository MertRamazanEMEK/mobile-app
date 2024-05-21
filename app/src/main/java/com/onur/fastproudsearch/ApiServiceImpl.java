package com.onur.fastproudsearch;

import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiServiceImpl implements ApiService {

    private static final OkHttpClient client = new OkHttpClient();
    private static final String URL_STRING = "https://api.escuelajs.co/api/v1/products";
    private static final Gson gson = new Gson();
    private String json = null;

    @Override
    public void getResponse(String search) throws IOException {
        search = (String) URL_STRING+search;
        Request request = new Request.Builder().url(search).get().build();
        Response response= null;
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                assert response.body() != null;
                json=response.body().string();
            }
        });
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
