package com.polytechnic.astra.ac.id.internak.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.polytechnic.astra.ac.id.internak.Fragment.HomeFragment;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.UserViewModel;

public class LoginFragment extends Fragment {

    private EditText emailEditText;
    private EditText passwordEditText;
    private ImageButton showPasswordButton;
    private Button loginButton;
    private UserViewModel userViewModel;
    private boolean isPasswordVisible = false;

    private static final String TAG = "LoginFragment";

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        emailEditText = view.findViewById(R.id.emailEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        showPasswordButton = view.findViewById(R.id.showPasswordButton);
        loginButton = view.findViewById(R.id.loginButton);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        showPasswordButton.setOnClickListener(v -> togglePasswordVisibility());

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
                userViewModel.loginUser(email, password);
            }
        });

        userViewModel.getUserData().observe(getViewLifecycleOwner(), userVO -> {
            if (userVO != null) {
                String welcomeMessage = "Selamat datang " + userVO.getUsrNamaDepan();
                Toast.makeText(getActivity(), welcomeMessage, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Login successful, navigating to HomeActivity");
                navigateToNextActivity();
                clearFields();
            } else {
                Toast.makeText(getActivity(), "Email atau Kata sandi Salah", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Login failed");
                clearFields();
            }
        });

        return view;
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    private void navigateToNextActivity() {
        try {
            HomeFragment homeFragment = new HomeFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_login, homeFragment)
                    .addToBackStack(null)
                    .commit();
        } catch (Exception e) {
            Log.e(TAG, "Error starting HomeFragment", e);
        }
    }

    private void clearFields() {
        emailEditText.setText("");
        passwordEditText.setText("");
    }
    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            showPasswordButton.setImageResource(R.drawable.icon_mata);
        } else {
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            showPasswordButton.setImageResource(R.drawable.icon_mata);
        }
        passwordEditText.setSelection(passwordEditText.length());
        isPasswordVisible = !isPasswordVisible;
    }
}
