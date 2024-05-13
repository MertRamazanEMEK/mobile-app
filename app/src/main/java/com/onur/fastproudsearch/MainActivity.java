package com.onur.fastproudsearch;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton, searchButton;
    private LinearLayout loginLayout, searchLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML dosyasındaki bileşenleri Java değişkenlerine atama
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        searchButton = findViewById(R.id.searchButton);
        loginLayout = findViewById(R.id.login_layout);
        searchLayout = findViewById(R.id.search_layout);

        // Giriş butonuna tıklama işlemi
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kullanıcı adı ve şifre kontrolü
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Burada gerçek bir kimlik doğrulama yapılabilir, şimdilik basit bir kontrol sağlıyoruz
                if (!username.isEmpty() && !password.isEmpty()) {
                    // Giriş başarılıysa, ürün arama ekranını göster
                    showSearchScreen();
                } else {
                    // Giriş başarısızsa, kullanıcıya uygun bir mesaj gösterebilirsiniz
                    // Burada sadece basit bir Toast mesajı gösteriyoruz
                    Toast.makeText(MainActivity.this, "Lütfen kullanıcı adı ve şifre girin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Ürün arama butonuna tıklama işlemi
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ürün arama işlemi gerçekleştirilebilir
                // Burada sadece örneğin ekranı temizliyoruz
                Toast.makeText(MainActivity.this, "Ürün arama işlemi gerçekleştirilecek", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Ürün arama ekranını gösteren metod
    private void showSearchScreen() {
        loginLayout.setVisibility(View.GONE); // Giriş ekranını gizle
        searchLayout.setVisibility(View.VISIBLE); // Ürün arama ekranını göster
    }
}
