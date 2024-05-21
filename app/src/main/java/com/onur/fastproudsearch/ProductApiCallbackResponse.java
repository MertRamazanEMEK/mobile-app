package com.onur.fastproudsearch;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductApiCallbackResponse {

    private static final String TAG="ProductApiCallbackResponse";
    List<Product> productList = null;
    List<Product> getProducts(String url){
        ProductApi apiService = ProductApiCallback.getApiService();
        Call<List<Product>> call = apiService.getProducts(url);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()){
                    productList = response.body();
                }
                else {
                    Log.e(TAG,response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG,"Api call error.");
            }
        });
        return productList;
    }
}
