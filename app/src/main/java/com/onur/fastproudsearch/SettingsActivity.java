package com.onur.fastproudsearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
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
    private TextView usernameLabel, passwordLabel;

    private EditText usernameEditText, passwordEditText;
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
        editProfileButton = findViewById(R.id.editProfileButton);
        helpButton = findViewById(R.id.helpButton);

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        boolean isDarkMode = sharedPreferences.getBoolean("isDarkMode", false);
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

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedLanguage = parentView.getItemAtPosition(position).toString();
                Toast.makeText(SettingsActivity.this, "Seçilen Dil: " + selectedLanguage, Toast.LENGTH_SHORT).show();
                Configuration configuration = getResources().getConfiguration();
                Locale locale;
                if (selectedLanguage.equals("English")) {
                    // İngilizce seçildiğinde
                    locale = new Locale("en");
                } else if (selectedLanguage.equals("Turkish")) {
                    // Türkçe seçildiğinde
                    locale = new Locale("tr", "TR"); // Türkçe'nin dil kodu ve ülke kodu
                } else {
                    // Varsayılan olarak İngilizce
                    locale = new Locale("en");
                }
                configuration.setLocale(locale);
                getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());

                // Spinner'da seçilen dilin konumunu kaydet
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("language_position", position);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Spinner'da hiçbir şey seçilmediğinde yapılacak işlemler
                // Varsayılan olarak İngilizce seçilsin
                Configuration configuration = getResources().getConfiguration();
                Locale locale = new Locale("en");
                configuration.setLocale(locale);
                getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
                // Spinner'ın ilk öğesinin seçili olduğu pozisyonu kaydet
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("language_position", 0); // İlk öğe seçili olduğu için pozisyon 0
                editor.apply();
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

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, EditProfileActivity.class);
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

    private void initComponents(){
        // Diğer bileşenlerin tanımlamaları burada
        usernameLabel = findViewById(R.id.usernameEditText);
        passwordLabel = findViewById(R.id.passwordEditText);
    }

    private void registerEventHandler(){
        // Eğer gerekliyse bileşenler için olay dinleyicilerini burada kaydedin
        // Örneğin, bir butona tıklama dinleyicisi ekleyebilirsiniz.
    }

    private void loadUserData() {
        // Kullanıcı adı ve şifre bilgilerini al ve TextView bileşenlerine ata
        SharedPreferences prefs = getSharedPreferences("user_credentials", MODE_PRIVATE);
        String username = prefs.getString("username", "");
        String password = prefs.getString("password", "");

        // Kullanıcı adı ve şifreyi ilgili TextView bileşenlerine ata
        usernameLabel.setText("Kullanıcı Adı: " + username);
        passwordLabel.setText("Şifre: " + password);
    }
}
