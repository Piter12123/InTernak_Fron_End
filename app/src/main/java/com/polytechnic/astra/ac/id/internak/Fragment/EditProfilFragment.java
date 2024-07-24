package com.polytechnic.astra.ac.id.internak.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.polytechnic.astra.ac.id.internak.API.VO.UserVO;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.UserViewModel;

public class EditProfilFragment extends Fragment {
    private static final String TAG = "EditProfilFragment";

    private EditText usrNamaEditText, usrEmailEditText, noTelpEditText;
    private Button btnUpdateProfile;
    private UserViewModel userViewModel;
    private UserVO currentUser;

    public EditProfilFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profil, container, false);

        usrNamaEditText = view.findViewById(R.id.username);
        usrEmailEditText = view.findViewById(R.id.email);
        noTelpEditText = view.findViewById(R.id.notelpon);
        btnUpdateProfile = view.findViewById(R.id.save_button);
        ImageButton btnBack = view.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginSession", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("dataUser", null);
        if (userJson != null) {
            Gson gson = new Gson();
            currentUser = gson.fromJson(userJson, UserVO.class);
            usrNamaEditText.setText(currentUser.getUsrNama());
            usrEmailEditText.setText(currentUser.getUsrEmail());
            noTelpEditText.setText(currentUser.getNoTelp());
        }

        btnUpdateProfile.setOnClickListener(v -> updateUserProfile());

        return view;
    }

    private void updateUserProfile() {
        String usrNama = usrNamaEditText.getText().toString().trim();
        String usrEmail = usrEmailEditText.getText().toString().trim();
        String noTelp = noTelpEditText.getText().toString().trim();

        if (TextUtils.isEmpty(usrNama) || TextUtils.isEmpty(usrEmail) || TextUtils.isEmpty(noTelp)) {
            Toast.makeText(getActivity(), "Semua field wajib diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        currentUser.setUsrNama(usrNama);
        currentUser.setUsrEmail(usrEmail);
        currentUser.setNoTelp(noTelp);

        userViewModel.updateUserProfile(currentUser);

        userViewModel.getUserData().observe(getViewLifecycleOwner(), userVO -> {
            if (userVO != null) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginSession", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String userJson = gson.toJson(userVO);
                editor.putString("dataUser", userJson);
                editor.apply();
                Toast.makeText(getActivity(), "Profil berhasil diperbarui", Toast.LENGTH_SHORT).show();
                getParentFragmentManager().popBackStack();
                navigateToNextActivity();
            } else {
                Toast.makeText(getActivity(), "Gagal memperbarui profil", Toast.LENGTH_SHORT).show();
            }
        });
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
