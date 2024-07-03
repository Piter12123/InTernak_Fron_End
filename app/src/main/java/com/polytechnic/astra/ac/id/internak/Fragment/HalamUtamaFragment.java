package com.polytechnic.astra.ac.id.internak.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.polytechnic.astra.ac.id.internak.R;

public class HalamUtamaFragment extends Fragment {

    private Button daftarButton;
    private Button loginButton;

    public HalamUtamaFragment() {
        // Required empty public constructor
    }

    public static HalamUtamaFragment newInstance() {
        return new HalamUtamaFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_halam_utama, container, false);

        daftarButton = view.findViewById(R.id.registerButton);
        loginButton = view.findViewById(R.id.loginButton);

        daftarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new RegisterFragment());
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new LoginFragment());
            }
        });

        return view;
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_login, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
