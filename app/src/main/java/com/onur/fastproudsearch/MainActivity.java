package com.onur.fastproudsearch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements ProductCallback{

    private TextInputLayout searchTextInputLayout;
    private RecyclerView productsRecyclerView;
    private SwipeRefreshLayout produsctsSwipeRefreshLayout;
    private Button settingsButton;
    private Button favoritesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initComponents();
        registerEventHandler();
        fetchProducts("");
    }

    private void fetchProducts(String url) {
        ProductApiCallbackResponse apiCallbackResponse = new ProductApiCallbackResponse();
        apiCallbackResponse.getProducts(url, this);
    }

    private void initComponents(){
        // Views initialization
        searchTextInputLayout = findViewById(R.id.searchTextInputLayout);
        productsRecyclerView = findViewById(R.id.productsRecyclerView);
        produsctsSwipeRefreshLayout = findViewById(R.id.productSwipeRefreshLayout);
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

        searchTextInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchProducts(Objects.requireNonNull(searchTextInputLayout.getEditText()).getText().toString());
            }
        });
    }

    @Override
    public void onProductsReceived(List<Product> productList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        productsRecyclerView.setLayoutManager(linearLayoutManager);
        productsRecyclerView.setHasFixedSize(true);
        ProductRecyclerViewAdaptor adaptor = new ProductRecyclerViewAdaptor(productList);
        productsRecyclerView.setAdapter(adaptor);
        productsRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,LinearLayoutManager.VERTICAL));
    }

    @Override
    public void onApiCallFailed(String errorMessage) {
        Toast.makeText(MainActivity.this, "Ürünler Yüklenemedi.", Toast.LENGTH_SHORT).show();
    }
}
