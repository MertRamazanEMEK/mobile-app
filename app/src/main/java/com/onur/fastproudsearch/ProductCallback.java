package com.onur.fastproudsearch;

import java.util.List;

public interface ProductCallback {
    void onProductsReceived(List<Product> productList);
    void onApiCallFailed(String errorMessage);
}
