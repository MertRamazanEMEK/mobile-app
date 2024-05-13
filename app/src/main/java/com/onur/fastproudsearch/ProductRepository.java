package com.onur.fastproudsearch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {
    private ProductApiService apiService;

    public ProductRepository() {
        apiService = RetrofitClient.getRetrofitInstance().create(ProductApiService.class);
    }

    public void searchProducts(String query, String apiKey, final ProductCallback callback) {
        Call<ProductResponse> call = apiService.searchProducts(query, apiKey);
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getProducts());
                } else {
                    callback.onError("API Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                callback.onError("Network Error: " + t.getMessage());
            }
        });
    }
}

