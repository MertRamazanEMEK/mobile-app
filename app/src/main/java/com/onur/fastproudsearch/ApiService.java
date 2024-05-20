package com.onur.fastproudsearch;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;

public interface ApiService {
    Response getResponse(String search) throws IOException;
    ProductList getProducts(String search) throws IOException;
    Product getProduct(String id) throws IOException;

}
