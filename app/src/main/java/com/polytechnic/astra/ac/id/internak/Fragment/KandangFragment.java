package com.polytechnic.astra.ac.id.internak.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import com.polytechnic.astra.ac.id.internak.API.VO.KandangVO;
import com.polytechnic.astra.ac.id.internak.Adapter.KandangAdapter;
import com.polytechnic.astra.ac.id.internak.Model.UserModel;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.KandangViewModel;

import java.util.ArrayList;
import java.util.List;

public class KandangFragment extends Fragment implements KandangAdapter.OnKandangClickListener{

    private RecyclerView recyclerView;
    private ImageButton add;
    private EditText etSearch;
    private KandangAdapter kandangAdapter;
    private KandangViewModel kandangViewModel;
    private List<KandangVO> kandangList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kandang, container, false);

        add = view.findViewById(R.id.fabAddKandang);
        etSearch = view.findViewById(R.id.edtSearchKandang);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new TambahKandangFragment());
            }
        });

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Tidak ada aksi yang diperlukan
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterKandang(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Tidak ada aksi yang diperlukan
            }
        });

        kandangViewModel = new ViewModelProvider(this).get(KandangViewModel.class);
        kandangViewModel.getKandangListData().observe(getViewLifecycleOwner(), new Observer<List<KandangVO>>() {
            @Override
            public void onChanged(List<KandangVO> kandangList) {
                if (kandangList != null) {
                    kandangAdapter = new KandangAdapter(getParentFragment(),getContext(), kandangList, KandangFragment.this,KandangFragment.this);
                    recyclerView.setAdapter(kandangAdapter);
                    KandangFragment.this.kandangList = kandangList;
                } else {
                    Log.d("KandangFragment", "Tidak ada data Kandang yang ditemukan.");
                }
            }
        });

        UserModel loggedInUser = getLoggedInUser();
        if (loggedInUser != null) {
            loadKandangData(loggedInUser.getUsrId());
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

    private void loadKandangData(int userId) {
        kandangViewModel.loadKandangData(userId);
    }
    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_login, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onEditKandangClick(KandangVO kandang) {
        EditKandangFragment fragment = EditKandangFragment.newInstance(
                kandang.getKdgId(),
                kandang.getKdgNama(),
                kandang.getKdgJenis(),
                kandang.getKdgKapasitas(),
                kandang.getKdgLuas(),
                kandang.getKdgAlamat(),
                kandang.getKdgLattitude(),
                kandang.getKdgLongtitude(),
                kandang.getKdgSuhu(),
                kandang.getKdgStatus()
        );
        navigateToFragment(fragment);
    }
    public void onViewKandangClick(KandangVO kandang) {
        DetailKandangFragment fragment = DetailKandangFragment.newInstance(
                kandang.getKdgId(),
                kandang.getKdgNama(),
                kandang.getKdgJenis(),
                kandang.getKdgKapasitas(),
                kandang.getKdgAlamat(),
                kandang.getKdgSuhu()
        );
        navigateToFragment(fragment);
    }

    private void filterKandang(String query) {
        List<KandangVO> filteredList = new ArrayList<>();
        for (KandangVO kandang : kandangList) {
            if (kandang.getKdgNama().toLowerCase().contains(query.toLowerCase()) ||
                    String.valueOf(kandang.getKdgId()).contains(query)) {
                filteredList.add(kandang);
            }
        }
        kandangAdapter.filterList(filteredList);
}
}
