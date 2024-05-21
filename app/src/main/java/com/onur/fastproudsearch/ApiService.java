package com.onur.fastproudsearch;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;

public interface ApiService {
    void getResponse(String search) throws IOException;

}
