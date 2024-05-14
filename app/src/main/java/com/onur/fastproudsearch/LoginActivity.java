package com.onur.fastproudsearch;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton,signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();
        registerEventHandler();

        int nightModeFlags =
                getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                // Cihaz karanlık modda, karanlık tema kullanılmalı
                setTheme(R.style.AppTheme_Dark);
                break;

            case Configuration.UI_MODE_NIGHT_NO:
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                // Cihaz açık temada, varsayılan tema kullanılmalı
                setTheme(R.style.AppTheme_Light);
                break;
        }
    }

    private void initComponents(){
        // XML dosyasındaki bileşenleri Java değişkenlerine atama
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);
    }

    private void registerEventHandler(){
        // Giriş butonuna tıklama işlemi
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kullanıcı adı ve şifre kontrolü
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Kullanıcı adı ve şifre boş değilse devam et
                if (!username.isEmpty() && !password.isEmpty()) {
                    // MainActivity'e geçiş yapmak için Intent oluştur
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent); // MainActivity'e geçiş yap
                } else {
                    // Kullanıcıya uygun bir mesaj göster
                    Toast.makeText(LoginActivity.this, "Lütfen kullanıcı adı ve şifre girin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Kaydol butonuna tıklama işlemi
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SignupActivity'e geçiş yapmak için Intent oluştur
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent); // SignupActivity'e geçiş yap
            }
        });
    }
}
