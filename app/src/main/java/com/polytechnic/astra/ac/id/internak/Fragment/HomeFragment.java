package com.polytechnic.astra.ac.id.internak.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.polytechnic.astra.ac.id.internak.R;

public class HomeFragment extends Fragment {

    private static final String ARG_USER_NAME = "user_name";
    private String userName;
    private Button MonitoringKdg,MonitoringKstn;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String userName) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER_NAME, userName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userName = getArguments().getString(ARG_USER_NAME);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        MonitoringKdg = view.findViewById(R.id.monitoringKandangButton);

        MonitoringKdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new KandangFragment());
            }
        });

        TextView welcomeText = view.findViewById(R.id.welcomeText);
        if (userName != null) {
            welcomeText.setText("Selamat Datang, " + userName);
        }

        return view;
    }
    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_login, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}