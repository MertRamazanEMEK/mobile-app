package com.onur.fastproudsearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    private SwitchCompat themeSwitch;
    private Button backButton;
    private Spinner languageSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tema ayarlarını kontrol et
        checkTheme();

        setContentView(R.layout.activity_settings);
        themeSwitch = findViewById(R.id.themeSwitch);
        backButton = findViewById(R.id.backButton);
        languageSpinner = findViewById(R.id.languageSpinner);

        // Set up the language options
        String[] languageOptions = getResources().getStringArray(R.array.language_options);
        ArrayAdapter<String> languageAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, languageOptions);
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(languageAdapter);

        // Handle language spinner selection changes (optional)
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle selected language here (e.g., save preference)
                String selectedLanguage = languageOptions[position];
                // Implement logic to handle chosen language (e.g., update UI, store preference)
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing here or handle if no selection is made
            }
        });

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

        // Tema anahtarının durumunu kontrol et ve ayarla
        boolean darkThemeEnabled = getThemeState();
        themeSwitch.setChecked(darkThemeEnabled);
        if (darkThemeEnabled) {
            // Eğer karanlık tema açıksa karanlık modu etkinleştir
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            // Aksi halde açık modu etkinleştir
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        // Tema anahtarının değişikliklerini dinleme
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Eğer anahtar işaretliyse, karanlık tema kullan
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    setThemeState(true); // Tema durumunu kaydetme
                } else {
                    // Eğer anahtar işaretli değilse, açık tema kullan
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    setThemeState(false); // Tema durumunu kaydetme
                }
                recreate(); // Yeniden oluşturma işlemi, tema değişikliğinin etkisini hemen göstermek için
            }
        });
    }

    // Tema durumunu kontrol et
    private void checkTheme() {
        int nightModeFlags = getResources().getConfiguration().uiMode &
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

    // Tema durumunu kaydetmek için yardımcı metot
    private void setThemeState(boolean darkThemeEnabled) {
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
