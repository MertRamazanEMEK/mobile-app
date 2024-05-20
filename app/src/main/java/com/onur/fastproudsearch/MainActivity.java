package com.onur.fastproudsearch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout searchTextInputLayout;
    private Button settingsButton, favoritesButton;
    private ArrayAdapter<String> productsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        registerEventHandler();


    }

    private void initComponents(){
        // XML'den görünümleri al
        searchTextInputLayout = findViewById(R.id.searchTextInputLayout);
        settingsButton = findViewById(R.id.settingsButton);
        favoritesButton = findViewById(R.id.favoritesButton);



    }

    private void registerEventHandler(){
        // Arama butonuna tıklama işlemi ekle
        searchTextInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Ayarlar butonuna tıklama işlemi ekle
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ayarlar butonuna tıklandığında yapılacak işlemler buraya yazılır
                Toast.makeText(MainActivity.this, "Ayarlar butonuna tıklandı", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent); // MainActivity'e geçiş yap
            }
        });

        // Favoriler butonuna tıklama işlemi ekle
        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Favoriler butonuna tıklandığında yapılacak işlemler buraya yazılır
                Toast.makeText(MainActivity.this, "Favoriler butonuna tıklandı", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(intent); // MainActivity'e geçiş yap
            }
        });
    }

}
