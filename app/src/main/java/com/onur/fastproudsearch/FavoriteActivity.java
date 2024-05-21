package com.onur.fastproudsearch;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class FavoriteActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private ArrayList<String> favoriteItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        // Favori ürünlerin listesi
        ListView favoriteListView = findViewById(R.id.favoriteListView);

        // Favori ürünlerin listesi verileri
        favoriteItems = new ArrayList<>();
        // Burada favori ürünlerin listesini almak veya başka bir kaynaktan yüklemek için kod ekleyebilirsiniz

        // Favori ürünlerin listesi adaptörü
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, favoriteItems);
        favoriteListView.setAdapter(adapter);

        // Favori ürünlerin listesini gösterme işlemi
        displayFavoriteItems();

    }

    // Favori ürünlerin listesini gösterme metodu
    private void displayFavoriteItems() {
        // Örnek olarak, favori ürünlerin listesini bir dizi içinde tanımladık
        String[] items = {"Favori Ürün 1", "Favori Ürün 2", "Favori Ürün 3", "Favori Ürün 4"};

        // Dizi içindeki her bir öğeyi favori ürünler listesine ekliyoruz
        favoriteItems.addAll(Arrays.asList(items));

        // Adaptörü güncelle ve değişiklikleri listeye yansıt
        adapter.notifyDataSetChanged();
    }
}
