package com.onur.fastproudsearch;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout searchTextInputLayout;
    private Button searchButton;
    private ImageButton settingsButton, favoritesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML'den görünümleri al
        searchTextInputLayout = findViewById(R.id.searchTextInputLayout);
        searchButton = findViewById(R.id.searchButton);
        settingsButton = findViewById(R.id.settingsButton);
        favoritesButton = findViewById(R.id.favoritesButton);

        // Arama butonuna tıklama işlemi ekle
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });

        // Ayarlar butonuna tıklama işlemi ekle
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ayarlar butonuna tıklandığında yapılacak işlemler buraya yazılır
                Toast.makeText(MainActivity.this, "Ayarlar butonuna tıklandı", Toast.LENGTH_SHORT).show();

            }
        });

        // Favoriler butonuna tıklama işlemi ekle
        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Favoriler butonuna tıklandığında yapılacak işlemler buraya yazılır
                Toast.makeText(MainActivity.this, "Favoriler butonuna tıklandı", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void performSearch() {
        // Burada yapılacak olan ürün arama işlemi kodlanır
        // Örnek olarak, şu anda sadece bir Toast mesajı gösteriyoruz
        String searchQuery = searchTextInputLayout.getEditText().getText().toString().trim();
        if (!searchQuery.isEmpty()) {
            // Ürün arama işlemi gerçekleştirilir
            // searchProduct(searchQuery);
            Toast.makeText(MainActivity.this, "Aranan ürün: " + searchQuery, Toast.LENGTH_SHORT).show();
        } else {
            // Kullanıcı bir şey aramadan arama butonuna tıklarsa bir uyarı gösterilir
            Toast.makeText(MainActivity.this, "Lütfen bir ürün adı girin", Toast.LENGTH_SHORT).show();
        }
    }

    // Ürün arama metodunu buraya ekleyebilirsiniz
    // private void searchProduct(String query) { ... }
}