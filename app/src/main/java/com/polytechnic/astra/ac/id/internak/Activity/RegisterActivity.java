package com.polytechnic.astra.ac.id.internak.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.text.InputType;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.polytechnic.astra.ac.id.internak.API.VO.UserVO;
import com.polytechnic.astra.ac.id.internak.MainActivity;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.UserViewModel;

public class RegisterActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private CheckBox termsCheckBox;
    private TextView termsError,alreadyHaveAccount;
    private EditText edtNamaDepan, edtNamaBlkg, edtKodePos, edtNoTelp, edtEmail, edtNama, edtPassword;
    private Button btnRegister;
    private ImageButton showPasswordButton;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        edtNamaDepan = findViewById(R.id.firstName);
        edtNamaBlkg = findViewById(R.id.lastName);
        edtKodePos = findViewById(R.id.postalCode);
        edtNoTelp = findViewById(R.id.phoneNumber);
        edtEmail = findViewById(R.id.email);
        edtNama = findViewById(R.id.username);
        edtPassword = findViewById(R.id.password);
        termsCheckBox = findViewById(R.id.termsCheckBox);
        termsError = findViewById(R.id.termsError);
        btnRegister = findViewById(R.id.registerButton);
        showPasswordButton = findViewById(R.id.showPasswordButton);
        alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);

        btnRegister.setOnClickListener(v -> registerUser());
        showPasswordButton.setOnClickListener(v -> togglePasswordVisibility());
        alreadyHaveAccount.setOnClickListener(v -> login());

        userViewModel.getUserData().observe(this, user -> {
            if (user != null) {
                Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                Sukses();
            } else {
                Toast.makeText(this, "Registrasi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registerUser() {
        String namaDepan = edtNamaDepan.getText().toString().trim();
        String namaBlkg = edtNamaBlkg.getText().toString().trim();
        String kodePos = edtKodePos.getText().toString().trim();
        String noTelp = edtNoTelp.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String nama = edtNama.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(namaDepan)) {
            edtNamaDepan.setError("Nama Depan wajib Di isi");
            edtNamaDepan.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(namaBlkg)) {
            edtNamaBlkg.setError("Nama Belakang wajib Di isi");
            edtNamaBlkg.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(kodePos)) {
            edtKodePos.setError("Kode Pos wajib Di isi");
            edtKodePos.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(noTelp)) {
            edtNoTelp.setError("No Telpon wajib Di isi");
            edtNoTelp.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Email wajib Di isi");
            edtEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(nama)) {
            edtNama.setError("Nama wajib Di isi");
            edtNama.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            edtPassword.setError("Password wajib Di isi");
            edtPassword.requestFocus();
            return;
        }
        if (!isValidEmail(email)) {
            edtEmail.setError("Format Email Tidak Valid abc@gmail.com");
            edtEmail.requestFocus();
            return;
        }
        if (!termsCheckBox.isChecked()) {
            termsError.setVisibility(View.VISIBLE);
            termsCheckBox.requestFocus();
            return;
        } else {
            termsError.setVisibility(View.GONE);
        }
        UserVO user = new UserVO(null, namaDepan, namaBlkg, kodePos, noTelp, email, nama, password, null);
        userViewModel.registerUser(user);

        String registeredName = namaDepan + " " + namaBlkg;
        Intent intent = new Intent(RegisterActivity.this, RegisterBerhasilActivity.class);
        intent.putExtra("REGISTERED_NAME", registeredName);
        startActivity(intent);
    }
    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }
    private void clearInputFields() {
        edtNamaDepan.setText("");
        edtNamaBlkg.setText("");
        edtKodePos.setText("");
        edtNoTelp.setText("");
        edtEmail.setText("");
        edtNama.setText("");
        edtPassword.setText("");
        termsCheckBox.setChecked(false);
    }
    private void Sukses() {
        Intent intent = new Intent(RegisterActivity.this, RegisterBerhasilActivity.class);
        startActivity(intent);
        finish();
    }
    private void login() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            showPasswordButton.setImageResource(R.drawable.icon_mata);
        } else {
            edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            showPasswordButton.setImageResource(R.drawable.icon_mata);
        }
        edtPassword.setSelection(edtPassword.length());
        isPasswordVisible = !isPasswordVisible;
    }
}
