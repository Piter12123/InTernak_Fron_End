package com.polytechnic.astra.ac.id.internak.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanSakitVO;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import com.polytechnic.astra.ac.id.internak.Adapter.HewanAdapter;
import com.polytechnic.astra.ac.id.internak.Adapter.HewanSakitAdapter;
import com.polytechnic.astra.ac.id.internak.Model.UserModel;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.HewanSakitViewModel;
import com.polytechnic.astra.ac.id.internak.ViewModel.HewanViewModel;

import java.util.ArrayList;
import java.util.List;

public class HewanSakitFragment extends Fragment {

    private RecyclerView recyclerView;
    private HewanSakitAdapter hewansakitAdapter;
    private ImageButton add;
    private EditText etSearch;
    private com.google.android.material.bottomnavigation.BottomNavigationView BottomNavigationView;
    private HewanSakitViewModel hewansakitViewModel;
    private List<HewanSakitVO> hewansakitList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hewan_sakit, container, false);

        add = view.findViewById(R.id.fabAddKandang);
        etSearch = view.findViewById(R.id.etSearch);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new TambahHewanSakitFragment());
            }
        });
        ImageButton btnBack = view.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterHewan(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        hewansakitViewModel = new ViewModelProvider(this).get(HewanSakitViewModel.class);
        hewansakitViewModel.getHewanSakitListData().observe(getViewLifecycleOwner(), new Observer<List<HewanSakitVO>>() {
            @Override
            public void onChanged(List<HewanSakitVO> hewanList) {
                if (hewanList != null) {
                    hewansakitAdapter = new HewanSakitAdapter(getContext(), hewanList);
                    recyclerView.setAdapter(hewansakitAdapter);
                    HewanSakitFragment.this.hewansakitList = hewanList;
                } else {
                    Log.d("HewanFragment", "Tidak ada data Hewan yang ditemukan.");
                }
            }
        });

        UserModel loggedInUser = getLoggedInUser();
        if (loggedInUser != null) {
            loadHewanData(loggedInUser.getUsrId());
        } else {
            Log.d("KandangFragment", "Tidak ada pengguna yang sedang login.");
        }

        return view;
    }
    private UserModel getLoggedInUser() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginSession", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("dataUser", null);
        if (userJson != null) {
            Gson gson = new Gson();
            return gson.fromJson(userJson, UserModel.class);
        }
        return null;
    }
    private void loadHewanData(int IdUser) {
        hewansakitViewModel.loadHewanSakitData(IdUser);
    }

    private void filterHewan(String query) {
        List<HewanSakitVO> filteredList = new ArrayList<>();
        for (HewanSakitVO hewan : hewansakitList) {
            if (hewan.getTmoDeskripsi().toLowerCase().contains(query.toLowerCase()) ||
                    String.valueOf(hewan.getHwnId()).contains(query)) {
                filteredList.add(hewan);
            }
        }
        hewansakitAdapter.filterList(filteredList);
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_login, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onViewHewanClick(HewanVO hewan) {
        DetailHewanFragment fragment = DetailHewanFragment.newInstance(
                hewan.getHwnId(),
                hewan.getHwnNama(),
                hewan.getHwnUsia(),
                hewan.getHwnBerat(),
                hewan.getHwnMasuk(),
                hewan.getHwnStatus()
        );
        navigateToFragment(fragment);
    }
}
