package com.polytechnic.astra.ac.id.internak.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.UserViewModel;
import com.polytechnic.astra.ac.id.internak.API.VO.UserVO;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private UserViewModel userViewModel;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                    emailEditText.setError("Email wajib Di isi");
                    passwordEditText.setError("Password wajib Di isi");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    emailEditText.setError("Email wajib Di isi");
                    emailEditText.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    passwordEditText.setError("Password wajib Di isi");
                    passwordEditText.requestFocus();
                    return;
                }
                if (!isValidEmail(email)) {
                    emailEditText.setError("Format Email Tidak Valid abc@gmail.com");
                    emailEditText.requestFocus();
                    return;
                }
                userViewModel.loginUser(email, password);//methot Login
            }
        });

        userViewModel.getUserData().observe(this, userVO -> {
            if (userVO != null) {
                String welcomeMessage = "Selamat datang " + userVO.getUsrNamaDepan();
                Toast.makeText(LoginActivity.this, welcomeMessage, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Login successful, navigating to TambahHewanActivity");
                navigateToNextActivity();
            } else {
                Toast.makeText(LoginActivity.this, "Email atau Kata sandi Salah", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Login failed");
                clearFields();
            }
        });
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    private void navigateToNextActivity() {
        try {
            Intent intent = new Intent(LoginActivity.this, TambahHewanActivity.class);
            Log.d(TAG, "Starting TambahHewanActivity");
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Log.e(TAG, "Error starting TambahHewanActivity", e);
        }
    }

    private void clearFields() {
        emailEditText.setText("");
        passwordEditText.setText("");
    }
}
