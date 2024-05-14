package com.onur.fastproudsearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    private SwitchCompat themeSwitch;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        themeSwitch = findViewById(R.id.themeSwitch);
        backButton = findViewById(R.id.backButton);

        // Geri butonuna tıklama dinleyicisi ekleme
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MainActivity'e geri dön
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Tema anahtarının durum değişikliğini dinleme
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Tema değişikliği işlemleri
                if (isChecked) {
                    // Eğer anahtar işaretliyse, karanlık tema kullan
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    saveThemeState(true); // Tema durumunu kaydetme
                } else {
                    // Eğer anahtar işaretli değilse, açık tema kullan
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    saveThemeState(false); // Tema durumunu kaydetme
                }
            }
        });

        // Kaydedilmiş tema durumunu kontrol etme ve uygulama
        boolean darkThemeEnabled = getThemeState();
        themeSwitch.setChecked(darkThemeEnabled);
        if (darkThemeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    // Tema durumunu kaydetmek için yardımcı metot
    private void saveThemeState(boolean darkThemeEnabled) {
        SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();
        editor.putBoolean("darkTheme", darkThemeEnabled);
        editor.apply();
    }

    // Kaydedilmiş tema durumunu almak için yardımcı metot
    private boolean getThemeState() {
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        return prefs.getBoolean("darkTheme", false);
    }
}
