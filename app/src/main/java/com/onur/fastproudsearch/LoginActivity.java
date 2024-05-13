package com.onur.fastproudsearch;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // XML dosyasındaki bileşenleri Java değişkenlerine atama
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        // Giriş butonuna tıklama işlemi
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kullanıcı adı ve şifre kontrolü
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Burada gerçek bir kimlik doğrulama yapılabilir, şimdilik basit bir kontrol sağlıyoruz
                if (!username.isEmpty() && !password.isEmpty()) {
                    // Giriş başarılıysa, ana aktiviteye geçiş yapabilirsiniz
                    // Burada örnek olarak LoginActivity'den MainActivity'e geçiş yapılıyor
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish(); // LoginActivity'i kapat
                } else {
                    // Giriş başarısızsa, kullanıcıya uygun bir mesaj gösterebilirsiniz
                    // Burada sadece basit bir Toast mesajı gösteriyoruz
                    Toast.makeText(LoginActivity.this, "Lütfen kullanıcı adı ve şifre girin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
