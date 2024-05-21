package com.onur.fastproudsearch;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductApiCallbackResponse {

    private static final String TAG = "ProductApiCallbackResponse";

    public void getProducts(String url, final ProductCallback callback) {
        ProductApi apiService = ProductApiCallback.getApiService();
        Call<List<Product>> call = apiService.getProducts(url);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> productList = response.body();
                    callback.onProductsReceived(productList);
                } else {
                    callback.onApiCallFailed(response.message());
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, "Api call error.", t);
                callback.onApiCallFailed("Api call error: " + t.getMessage());
            }
        });
    }
}
