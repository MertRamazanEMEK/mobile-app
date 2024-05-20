package com.onur.fastproudsearch;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;

public interface ApiService {
    Response getResponse(String add) throws IOException;
    ProductList getProducts() throws IOException;
    Product getProduct(String id) throws IOException;

}
