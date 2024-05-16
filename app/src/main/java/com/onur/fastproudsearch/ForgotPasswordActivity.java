package com.onur.fastproudsearch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Pattern;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private Button buttonResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotaccount);

        editTextEmail = findViewById(R.id.editTextEmail);
        buttonResetPassword = findViewById(R.id.buttonResetPassword);

        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                if (isValidEmail(email)) {
                    sendResetEmail(email);
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Geçersiz e-posta adresi.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private void sendResetEmail(String email) {
        // Şifre sıfırlama bağlantısının olduğu bir URL oluşturun
        String resetUrl = "http://example.com/reset_password?email=" + email;

        // E-posta gönderme işlemi için bir e-posta istemcisini başlatın
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + email));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Şifre Sıfırlama");
        intent.putExtra(Intent.EXTRA_TEXT, "Şifrenizi sıfırlamak için lütfen aşağıdaki bağlantıya tıklayın:\n\n" + resetUrl);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "E-posta istemcisini seçin"));
        } else {
            Toast.makeText(this, "E-posta istemcisi bulunamadı.", Toast.LENGTH_SHORT).show();
        }
    }
}
