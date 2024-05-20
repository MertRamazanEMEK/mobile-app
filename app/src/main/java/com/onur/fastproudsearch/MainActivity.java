package com.onur.fastproudsearch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout searchTextInputLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView productsRecyclerView;
    private Button settingsButton;
    private Button favoritesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views initialization
        searchTextInputLayout = findViewById(R.id.searchTextInputLayout);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        productsRecyclerView = findViewById(R.id.productsRecyclerView);
        settingsButton = findViewById(R.id.settingsButton);
        favoritesButton = findViewById(R.id.favoritesButton);

        // Setting up listeners or other actions as needed
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(intent);
            }
        });

        // Additional setup or functionality can be added here
    }
}
