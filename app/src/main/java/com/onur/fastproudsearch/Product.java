package com.onur.fastproudsearch;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    // Diğer ürün özellikleri buraya eklenebilir

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Diğer getter ve setter metotları buraya eklenebilir
}

