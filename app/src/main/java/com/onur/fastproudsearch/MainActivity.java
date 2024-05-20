package com.onur.fastproudsearch;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout searchTextInputLayout;
    private Button searchButton;
    private Button settingsButton, favoritesButton;
    private ListView productsListView;
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
        searchButton = findViewById(R.id.searchButton);
        settingsButton = findViewById(R.id.settingsButton);
        favoritesButton = findViewById(R.id.favoritesButton);
        productsListView = findViewById(R.id.productsListView);

        // Ürünler için ListView adaptörünü oluştur
        productsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        // ListView'e adaptörü bağla
        productsListView.setAdapter(productsAdapter);
    }

    private void registerEventHandler(){
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

        // Ürün listesindeki öğelere tıklama işlemi ekle
        productsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedProduct = productsAdapter.getItem(position);
                Toast.makeText(MainActivity.this, "Seçilen Ürün: " + selectedProduct, Toast.LENGTH_SHORT).show();
                // Burada seçilen ürünle ilgili bir eylem gerçekleştirebilirsiniz
            }
        });
    }

    private void performSearch() {
        String searchQuery = Objects.requireNonNull(searchTextInputLayout.getEditText()).getText().toString().trim();
        if (!searchQuery.isEmpty()) {
            // Arama yap
            new SearchTask().execute(searchQuery);
        } else {
            // Kullanıcı bir şey aramadan arama butonuna tıklarsa bir uyarı gösterilir
            Toast.makeText(MainActivity.this, "Lütfen bir ürün adı girin", Toast.LENGTH_SHORT).show();
        }
    }

    private class SearchTask extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            String searchQuery = params[0];
            ArrayList<String> productsList = new ArrayList<>();

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL("https://api.escuelajs.co/api/v1/products?search=" + searchQuery);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder buffer = new StringBuilder();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty. No point in parsing.
                    return null;
                }
                String jsonResponse = buffer.toString();

                // JSON yanıtını işleme
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONArray productsArray = jsonObject.getJSONArray("products");
                for (int i = 0; i < productsArray.length(); i++) {
                    JSONObject product = productsArray.getJSONObject(i);
                    String productName = product.getString("name");
                    productsList.add(productName);
                }
            } catch (IOException | JSONException e) {
                Log.e("MainActivity", "Error ", e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("MainActivity", "Error closing stream", e);
                    }
                }
            }

            return productsList;
        }

        @Override
        protected void onPostExecute(ArrayList<String> productsList) {
            if (productsList != null && !productsList.isEmpty()) {
                // Ürün listesini ListView adaptörüne ekle
                productsAdapter.clear();
                productsAdapter.addAll(productsList);
            } else {
                // Ürün bulunamadıysa bir hata mesajı göster
                Toast.makeText(MainActivity.this, "Ürün bulunamadı", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
