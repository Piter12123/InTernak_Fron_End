package com.polytechnic.astra.ac.id.internak.Fragment;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.FragmentTransaction;

import com.polytechnic.astra.ac.id.internak.API.VO.UserVO;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.UserViewModel;

public class RegisterFragment extends Fragment {

    private UserViewModel userViewModel;
    private CheckBox termsCheckBox;
    private TextView termsError, alreadyHaveAccount;
    private EditText edtNamaDepan, edtNamaBlkg, edtKodePos, edtNoTelp, edtEmail, edtNama, edtPassword;
    private Button btnRegister;
    private ImageButton showPasswordButton;
    private boolean isPasswordVisible = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        edtNamaDepan = view.findViewById(R.id.firstName);
        edtNamaBlkg = view.findViewById(R.id.lastName);
        edtKodePos = view.findViewById(R.id.postalCode);
        edtNoTelp = view.findViewById(R.id.phoneNumber);
        edtEmail = view.findViewById(R.id.email);
        edtNama = view.findViewById(R.id.username);
        edtPassword = view.findViewById(R.id.password);
        termsCheckBox = view.findViewById(R.id.termsCheckBox);
        termsError = view.findViewById(R.id.termsError);
        btnRegister = view.findViewById(R.id.registerButton);
        showPasswordButton = view.findViewById(R.id.showPasswordButton);
        alreadyHaveAccount = view.findViewById(R.id.alreadyHaveAccount);

        btnRegister.setOnClickListener(v -> registerUser());
        showPasswordButton.setOnClickListener(v -> togglePasswordVisibility());
        alreadyHaveAccount.setOnClickListener(v -> login());

        userViewModel.getUserData().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                Toast.makeText(getContext(), "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                Sukses(user.getUsrNamaDepan() + " " + user.getUsrNamaBlkg());
            } else {
                Toast.makeText(getContext(), "Registrasi Gagal", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
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

    private void Sukses(String registeredName) {
        RegisterBerhasilFragment berhasilLoginFragment = RegisterBerhasilFragment.newInstance(registeredName);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_login, berhasilLoginFragment); // Ensure this ID is correct in your layout
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void login() {
        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_login, loginFragment); // Ensure this ID is correct in your layout
        transaction.addToBackStack(null);
        transaction.commit();
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
