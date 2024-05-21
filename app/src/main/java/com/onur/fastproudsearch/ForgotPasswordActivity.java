package com.onur.fastproudsearch;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.Objects;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private Button buttonResetPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotaccount);

        editTextEmail = findViewById(R.id.editTextEmail);
        buttonResetPassword = findViewById(R.id.buttonResetPassword);

        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.isEmpty()){
                    Toast.makeText(ForgotPasswordActivity.this, "Lütfen Geçerli Bir Mail Giriniz", Toast.LENGTH_SHORT).show();
                    editTextEmail.requestFocus();
                    return;
                }

                mAuth = FirebaseAuth.getInstance();
                mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if (task.isSuccessful()){
                            SignInMethodQueryResult result = task.getResult();

                            if (Objects.requireNonNull(result.getSignInMethods()).isEmpty()){
                                Toast.makeText(ForgotPasswordActivity.this, "Girilen e-posta Sisteme Kayıtlı Değil" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }else {
                                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(ForgotPasswordActivity.this, "E-posta Gönderildi. Lütfen Mailinizi Giriniz", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(ForgotPasswordActivity.this, "E-posta Gönderilemedi. Lütfen Geçerli ve Kayıtlı Bir Posta Giriniz.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }else {
                            Toast.makeText(ForgotPasswordActivity.this, "Kullanıcı sorgusu başarısız oldu. Hata: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
