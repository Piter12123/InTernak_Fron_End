package com.polytechnic.astra.ac.id.internak.Fragment;

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
import com.polytechnic.astra.ac.id.internak.API.VO.KandangVO;
import com.polytechnic.astra.ac.id.internak.Adapter.KandangAdapter;
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

        loadKandangData();

        return view;
    }

    private void loadKandangData() {
        kandangViewModel.loadKandangData(1);
    }
    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_login, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
