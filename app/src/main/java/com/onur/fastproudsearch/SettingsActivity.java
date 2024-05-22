package com.onur.fastproudsearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    private SwitchCompat themeSwitch;
    private Spinner languageSpinner;
    private Button changePasswordButton, editProfileButton, helpButton;
    private EditText usernameEditText, passwordEditText;
    private TextView usernameLabel, passwordLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        int nightModeFlags =
                getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                break;

            case Configuration.UI_MODE_NIGHT_NO:
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                break;
        }
        themeSwitch = findViewById(R.id.themeSwitch);
        languageSpinner = findViewById(R.id.languageSpinner);
        changePasswordButton = findViewById(R.id.changePasswordButton);
        helpButton = findViewById(R.id.helpButton);

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        boolean isDarkMode = sharedPreferences.getBoolean("isDarkMode", false);
        AppCompatDelegate.setDefaultNightMode(isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        themeSwitch.setChecked(isDarkMode);

        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    setThemeState(true);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    setThemeState(false);
                }
            }
        });

        // Dil seçeneklerini bir string dizisi olarak tanımla
        String[] languageOptions = {"English", "Turkish"};

        // ArrayAdapter oluştur ve dil seçeneklerini bağla
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languageOptions);

        // Spinner'a ArrayAdapter'ı ayarla
        languageSpinner.setAdapter(adapter);

        // Dil tercihini kontrol et ve dil seçeneklerini doğru pozisyonda göster
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int languagePosition = preferences.getInt("language_position", 0); // Varsayılan olarak ilk dil seçeneği
        languageSpinner.setSelection(languagePosition);

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedLanguage = parentView.getItemAtPosition(position).toString();

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
                int previousLanguagePosition = preferences.getInt("language_position", 0);

                if (previousLanguagePosition != position) {
                    String message = getString(R.string.selected_language_message, selectedLanguage);
                    Toast.makeText(SettingsActivity.this, message, Toast.LENGTH_SHORT).show();
                    setLanguage(selectedLanguage);

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("language_position", position);
                    editor.apply();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Oturumu sonlandır
                FirebaseAuth.getInstance().signOut();

                // Oturum sonlandırıldıktan sonra login ekranına yönlendir
                Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(intent);

                // Aktiviteyi kapat
                finish();
            }
        });

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setThemeState(boolean isDarkMode) {
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isDarkMode", isDarkMode);
        editor.apply();
    }

    private void setLanguage(String selectedLanguage) {
        // Dil değişikliğini uygula
        Locale locale;
        if (selectedLanguage.equals("English")) {
            locale = new Locale("en");
        } else if (selectedLanguage.equals("Turkish")) {
            locale = new Locale("tr", "TR");
        } else {
            locale = new Locale("en");
        }
        updateResources(locale);
    }

    private void updateResources(Locale locale) {
        Resources res = getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());

        // Aktiviteyi yeniden başlat
        Intent refresh = new Intent(this, SplashActivity.class);
        startActivity(refresh);
        finish();
    }
}
