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
        ListView favoriteListView = findViewById(R.id.favoriteListView);
        favoriteItems = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, favoriteItems);
        favoriteListView.setAdapter(adapter);
        displayFavoriteItems();

    }
    private void displayFavoriteItems() {
        String[] items = {"Favori Ürün 1", "Favori Ürün 2", "Favori Ürün 3", "Favori Ürün 4"};
        favoriteItems.addAll(Arrays.asList(items));
        adapter.notifyDataSetChanged();
    }
}
