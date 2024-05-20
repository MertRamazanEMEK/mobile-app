package com.onur.fastproudsearch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout searchTextInputLayout;
    private SwipeRefreshLayout productSwipeRefreshLayout;
    private RecyclerView productsRecyclerView;
    private Button settingsButton;
    private Button favoritesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initComponents();
        registerEventHandler();
        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initComponents(){
        // Views initialization
        searchTextInputLayout = findViewById(R.id.searchTextInputLayout);
        productSwipeRefreshLayout = findViewById(R.id.productSwipeRefreshLayout);
        productsRecyclerView = findViewById(R.id.productsRecyclerView);
        settingsButton = findViewById(R.id.settingsButton);
        favoritesButton = findViewById(R.id.favoritesButton);
    }

    private void registerEventHandler(){

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
    }

    private void loadData() throws IOException {
        ApiServiceImpl apiService=new ApiServiceImpl();
        try {
            ProductList productList = apiService.getProducts("");
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
            productsRecyclerView.setLayoutManager(linearLayoutManager);
            productsRecyclerView.setHasFixedSize(true);
            ProductRecyclerViewAdaptor adaptor=new ProductRecyclerViewAdaptor(productList.productList);
            productsRecyclerView.setAdapter(adaptor);
            productsRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,LinearLayoutManager.VERTICAL));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
