package com.polytechnic.astra.ac.id.internak.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
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

import com.google.gson.Gson;
import com.polytechnic.astra.ac.id.internak.Model.UserModel;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.UserViewModel;

public class EditKatasandiFragment extends Fragment {
    private static final String TAG = "EditKatasandiFragment";

    private EditText oldPasswordEditText;
    private EditText newPasswordEditText;
    private EditText confirmPasswordEditText;
    private ImageButton toggleOldPasswordButton;
    private ImageButton toggleNewPasswordButton;
    private ImageButton toggleConfirmPasswordButton;
    private Button saveButton;
    private boolean isOldPasswordVisible = false;
    private boolean isNewPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;
    private UserViewModel userViewModel;

    public EditKatasandiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_katasandi, container, false);

        oldPasswordEditText = view.findViewById(R.id.sandilama);
        newPasswordEditText = view.findViewById(R.id.sandibaru);
        confirmPasswordEditText = view.findViewById(R.id.konfirmasi);
        saveButton = view.findViewById(R.id.save_button);
        toggleOldPasswordButton = view.findViewById(R.id.sandilamaview);
        toggleNewPasswordButton = view.findViewById(R.id.sandibaruview);
        toggleConfirmPasswordButton = view.findViewById(R.id.konfirmasiview);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        toggleOldPasswordButton.setOnClickListener(v -> togglePasswordVisibility(oldPasswordEditText, toggleOldPasswordButton));
        toggleNewPasswordButton.setOnClickListener(v -> togglePasswordVisibility(newPasswordEditText, toggleNewPasswordButton));
        toggleConfirmPasswordButton.setOnClickListener(v -> togglePasswordVisibility(confirmPasswordEditText, toggleConfirmPasswordButton));

        saveButton.setOnClickListener(v -> {
            String oldPassword = oldPasswordEditText.getText().toString().trim();
            String newPassword = newPasswordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(oldPassword) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmPassword)) {
                Toast.makeText(getActivity(), "Semua kolom wajib diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(getActivity(), "Kata sandi baru dan konfirmasi kata sandi tidak sama", Toast.LENGTH_SHORT).show();
                Clear();
                return;
            }

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginSession", Context.MODE_PRIVATE);
            String userJson = sharedPreferences.getString("dataUser", null);

            if (userJson != null) {
                UserModel userModel = new Gson().fromJson(userJson, UserModel.class);
                if (oldPassword.equals(userModel.getUsrPassword())) {
                    userViewModel.changePassword(oldPassword, newPassword);
                    Toast.makeText(getActivity(), "Kata sandi berhasil diubah", Toast.LENGTH_SHORT).show();
                    Clear();
                    navigateToNextActivity();
                } else {
                    Toast.makeText(getActivity(), "Kata sandi lama salah", Toast.LENGTH_SHORT).show();
                    Clear();
                }
            }
        });

        return view;
    }
    private void togglePasswordVisibility(EditText passwordEditText, ImageButton toggleButton) {
        if (passwordEditText.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            toggleButton.setImageResource(R.drawable.icon_mata);
        } else {
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            toggleButton.setImageResource(R.drawable.icon_mata);
        }
        passwordEditText.setSelection(passwordEditText.length());
    }
    private void Clear(){
        oldPasswordEditText.setText("");
        newPasswordEditText.setText("");
        confirmPasswordEditText.setText("");
    }
    private void navigateToNextActivity() {
        try {
            UserFragment userFragment = new UserFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_login, userFragment)
                    .addToBackStack(null)
                    .commit();
        } catch (Exception e) {
            Log.e(TAG, "Error starting UserFragment", e);
        }
    }
}
