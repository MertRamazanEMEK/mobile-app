package com.onur.fastproudsearch;

import java.io.IOException;
import java.util.List;

public interface ApiService {
    List<Product> getProducts() throws IOException;
}
