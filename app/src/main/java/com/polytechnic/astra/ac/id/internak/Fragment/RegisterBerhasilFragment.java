package com.polytechnic.astra.ac.id.internak.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.polytechnic.astra.ac.id.internak.R;

public class RegisterBerhasilFragment extends Fragment {

    private static final String ARG_REGISTERED_NAME = "REGISTERED_NAME";

    public static RegisterBerhasilFragment newInstance(String registeredName) {
        RegisterBerhasilFragment fragment = new RegisterBerhasilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_REGISTERED_NAME, registeredName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_berhasil, container, false);

        TextView registeredNameTextView = view.findViewById(R.id.tv_title);
        ImageButton nextButton = view.findViewById(R.id.btn_next);

        if (getArguments() != null) {
            String registeredName = getArguments().getString(ARG_REGISTERED_NAME);
            registeredNameTextView.setText("Selamat datang, " + registeredName);
        }

        nextButton.setOnClickListener(v -> navigateToLogin());

        return view;
    }

    private void navigateToLogin() {
        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_login, loginFragment); // Ensure this ID is correct in your layout
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
