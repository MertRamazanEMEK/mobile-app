package com.onur.fastproudsearch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductApiService {
    @GET("search")
    Call<ProductResponse> searchProducts(
            @Query("query") String query,
            @Query("apiKey") String apiKey
    );
}

