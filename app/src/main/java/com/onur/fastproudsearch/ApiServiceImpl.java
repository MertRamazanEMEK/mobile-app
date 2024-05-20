package com.onur.fastproudsearch;

import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiServiceImpl implements ApiService {

    private static final OkHttpClient client = new OkHttpClient();
    private static final String URL_STRING = "https://api.escuelajs.co/api/v1/products";
    private static final Gson gson = new Gson();

    @Override
    public Response getResponse(String search) throws IOException {
        Request request=new Request.Builder().url(URL_STRING+search).build();
        Response response=client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code"+response);
        return response;
    }

    @Override
    public ProductList getProducts(String search) throws IOException {
        Response response = getResponse(search);
        assert response.body() != null;
        return gson.fromJson(response.body().string(),ProductList.class);
    }

    @Override
    public Product getProduct(String id) throws IOException{
        Response response = getResponse(id);
        assert response.body() != null;
        return gson.fromJson(response.body().string(),Product.class);
    }

}
