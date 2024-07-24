package com.polytechnic.astra.ac.id.internak.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.polytechnic.astra.ac.id.internak.Model.UserModel;
import com.polytechnic.astra.ac.id.internak.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    private static final String ARG_USER_NAME = "user_name";
    private String userName;
    private Button MonitoringKdg, MonitoringKstn;
    private ImageView Notifikasii;
    private BottomNavigationView BottomNavigationView;

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
        } else {
            //Mengambil data dari sesion
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginSession", Context.MODE_PRIVATE);
            String userJson = sharedPreferences.getString("dataUser", null);
            if (userJson != null) {
                Gson gson = new Gson();
                UserModel userModel = gson.fromJson(userJson, UserModel.class);
                userName = userModel.getUsrNama();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        MonitoringKdg = view.findViewById(R.id.monitoringKandangButton);
        MonitoringKstn = view.findViewById(R.id.monitoringKesehatanHewanButton);
        Notifikasii = view.findViewById(R.id.notificationIcon);
        BottomNavigationView = view.findViewById(R.id.bottomNavigationView);

        MonitoringKstn.setOnClickListener(v -> navigateToFragment(new HewanSakitFragment()));
        MonitoringKdg.setOnClickListener(v -> navigateToFragment(new KandangFragment()));
        Notifikasii.setOnClickListener(v -> navigateToFragment(new NotifikasiFragment()));

        TextView welcomeText = view.findViewById(R.id.welcomeText);
        if (userName != null) {
            welcomeText.setText("Selamat Datang, " + userName);
        }

        BottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.homeid) {
                navigateToFragment(new HomeFragment());
                return true;
            } else if (itemId == R.id.cowid) {
                navigateToFragment(new HewanFragment());
                return true;
            } else if (itemId == R.id.userid) {
                navigateToFragment(new UserFragment());
                return true;
            }
            return false;
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
