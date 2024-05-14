package com.onur.fastproudsearch;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {

    private TextInputLayout usernameTextInputLayout, passwordTextInputLayout;
    private EditText usernameEditText, passwordEditText;
    private Button signupButton;

    // HashMap kullanıcı adlarını ve şifreleri saklamak için
    private HashMap<String, String> users = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
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
        // XML dosyasındaki bileşenleri Java değişkenlerine atama
        usernameTextInputLayout = findViewById(R.id.usernameTextInputLayout);
        passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signupButton = findViewById(R.id.signupButton);

        // Kaydol butonuna tıklama işlemi
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    // Kullanıcı kaydı yapma metodu
    private void signUp() {
        // Kullanıcı adı ve şifre al
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Kullanıcı adı ve şifre boş olup olmadığını kontrol et
        if (!username.isEmpty() && !password.isEmpty()) {
            // HashMap'e kullanıcı adı ve şifreyi ekle
            users.put(username, password);

            // Kayıt başarılı mesajı göster
            // Bu noktada normalde bir veritabanına veya dosyaya kaydetme işlemi yapılabilir
            // Şu anda sadece basit bir Toast mesajı gösteriyoruz
            showToast("Kayıt başarılı: Kullanıcı adı - " + username + ", Şifre - " + password);
        } else {
            // Kullanıcıya uygun bir mesaj göster
            showToast("Lütfen kullanıcı adı ve şifre girin");
        }
    }

    // Basit bir Toast mesajı gösterme metodu
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
