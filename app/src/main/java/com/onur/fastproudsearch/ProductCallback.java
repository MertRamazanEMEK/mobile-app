package com.onur.fastproudsearch;

import java.util.List;

public interface ProductCallback {
    void onSuccess(List<Product> products);
    void onError(String errorMessage);
}
