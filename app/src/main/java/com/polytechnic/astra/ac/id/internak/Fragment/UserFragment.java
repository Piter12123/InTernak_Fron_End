package com.polytechnic.astra.ac.id.internak.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.polytechnic.astra.ac.id.internak.Model.UserModel;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.UserViewModel;

public class UserFragment extends Fragment {

    private TextView namapengguna, ubahSandi, emailpengguna, ubahprofilid, deleteid;
    private Button btnkeluar;
    private BottomNavigationView BottomNavigationView;
    private UserViewModel userViewModel;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        namapengguna = view.findViewById(R.id.namapengguna);
        emailpengguna = view.findViewById(R.id.emailpengguna);
        btnkeluar = view.findViewById(R.id.btnkeluar);
        ubahSandi = view.findViewById(R.id.ubahsandiid);
        ubahprofilid = view.findViewById(R.id.ubahprofilid);
        deleteid = view.findViewById(R.id.deleteid);
        BottomNavigationView = view.findViewById(R.id.bottomNavigationView);

        // Inisialisasi UserViewModel
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Ambil data pengguna dari SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginSession", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("dataUser", null);
        if (userJson != null) {
            Gson gson = new Gson();
            UserModel userModel = gson.fromJson(userJson, UserModel.class);
            namapengguna.setText(userModel.getUsrNama());
            emailpengguna.setText(userModel.getUsrEmail());
        }

        // Implementasi tombol logout
        btnkeluar.setOnClickListener(v -> showLogoutConfirmationDialog());
        ubahprofilid.setOnClickListener(v -> navigateToFragment(new EditProfilFragment()));
        ubahSandi.setOnClickListener(v -> navigateToFragment(new EditKatasandiFragment()));
        deleteid.setOnClickListener(v -> showDeleteAccountConfirmationDialog());

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

    private void showLogoutConfirmationDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Oke", (dialog, which) -> logout())
                .setNegativeButton("Batal", null)
                .show();
    }

    private void logout() {
        // Hapus sesi
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginSession", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        getParentFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        // Arahkan ke LoginFragment
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_login, new LoginFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_login, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void showDeleteAccountConfirmationDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Konfirmasi Hapus Akun")
                .setMessage("Apakah Anda yakin ingin menghapus akun?")
                .setPositiveButton("Oke", (dialog, which) -> deleteAccount())
                .setNegativeButton("Batal", null)
                .show();
    }

    private void deleteAccount() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginSession", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("dataUser", null);
        if (userJson != null) {
            Gson gson = new Gson();
            UserModel userModel = gson.fromJson(userJson, UserModel.class);
            Integer userId = userModel.getUsrId();
            userViewModel.deleteUser(userId);
        }

        // Hapus sesi
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        getParentFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        // Arahkan ke LoginFragment
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_login, new LoginFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
