package com.onur.fastproudsearch;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

public class SignupActivity extends AppCompatActivity {

    private TextInputLayout usernameTextInputLayout, passwordTextInputLayout;
    private TextInputEditText usernameEditText, passwordEditText;
    private Button signupButton;
    private boolean passwordVisible = false;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        usernameTextInputLayout = findViewById(R.id.usernameTextInputLayout);
        passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signupButton = findViewById(R.id.signupButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        // PasswordEditText'te göz simgesine tıklanıldığında işlevi ekleme
        passwordTextInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });
    }

    private void togglePasswordVisibility() {
        if (passwordVisible) {
            // Gizliyse, görünür yap
            passwordEditText.setTransformationMethod(null);
            passwordVisible = false;
            passwordTextInputLayout.setEndIconDrawable(R.drawable.ic_visibility_off);
        } else {
            // Görünürse, gizle
            passwordEditText.setTransformationMethod(new PasswordTransformationMethod());
            passwordVisible = true;
            passwordTextInputLayout.setEndIconDrawable(R.drawable.ic_visibility_on);
        }
    }

    private void signUp() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (username.isEmpty()) {
            usernameTextInputLayout.setError("Kullanıcı adı boş olamaz.");
            usernameTextInputLayout.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            usernameTextInputLayout.setError("Geçerli bir email adresi girin.");
            usernameTextInputLayout.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordTextInputLayout.setError("Şifre boş olamaz.");
            passwordTextInputLayout.requestFocus();
            return;
        }

        if (password.length() < 6 || password.length() > 15) {
            passwordTextInputLayout.setError("Şifre 6 ila 15 karakter arasında olmalıdır.");
            passwordTextInputLayout.requestFocus();
            return;
        }

        // Firebase üzerinde kullanıcının var olup olmadığını kontrol etme
        mAuth.fetchSignInMethodsForEmail(username).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                if (task.isSuccessful()) {
                    SignInMethodQueryResult result = task.getResult();
                    if (result.getSignInMethods().isEmpty()) {
                        // Kullanıcı Firebase'de kayıtlı değilse, kaydı gerçekleştir
                        mAuth.createUserWithEmailAndPassword(username, password)
                                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Kayıt başarılı
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            Toast.makeText(SignupActivity.this, "Kayıt başarılı.", Toast.LENGTH_SHORT).show();
                                            // Burada isterseniz başka bir aktiviteye geçiş yapabilirsiniz.
                                        } else {
                                            // Kayıt başarısız
                                            Toast.makeText(SignupActivity.this, "Kayıt başarısız. Hata: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        // Kullanıcı Firebase'de zaten kayıtlı
                        Toast.makeText(SignupActivity.this, "Bu email adresi zaten kullanılıyor.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Kullanıcı sorgusu başarısız oldu
                    Toast.makeText(SignupActivity.this, "Kullanıcı sorgusu başarısız oldu. Hata: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
