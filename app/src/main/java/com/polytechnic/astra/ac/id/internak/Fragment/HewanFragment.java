package com.polytechnic.astra.ac.id.internak.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import com.polytechnic.astra.ac.id.internak.Adapter.HewanAdapter;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.HewanViewModel;

import java.util.ArrayList;
import java.util.List;

public class HewanFragment extends Fragment implements HewanAdapter.OnHewanClickListener {
    private RecyclerView recyclerView;
    private HewanAdapter hewanAdapter;
    private ImageButton add;
    private EditText etSearch;
    private HewanViewModel hewanViewModel;
    private List<HewanVO> hewanList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hewan, container, false);

        add = view.findViewById(R.id.fabAddKandang);
        etSearch = view.findViewById(R.id.etSearch);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new TambahHewanFragment());
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
                filterHewan(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Tidak ada aksi yang diperlukan
            }
        });

        hewanViewModel = new ViewModelProvider(this).get(HewanViewModel.class);
        hewanViewModel.getHewanListData().observe(getViewLifecycleOwner(), new Observer<List<HewanVO>>() {
            @Override
            public void onChanged(List<HewanVO> hewanList) {
                if (hewanList != null) {
                    // Initialize the adapter with the context, list of hewan, and this fragment as the listener
                    hewanAdapter = new HewanAdapter(getContext(), hewanList, HewanFragment.this,HewanFragment.this);
                    recyclerView.setAdapter(hewanAdapter);
                    HewanFragment.this.hewanList = hewanList;
                } else {
                    Log.d("HewanFragment", "Tidak ada data Hewan yang ditemukan.");
                }
            }
        });

        loadHewanData();

        return view;
    }

    private void loadHewanData() {
        hewanViewModel.loadHewanData(1);
    }

    private void filterHewan(String query) {
        List<HewanVO> filteredList = new ArrayList<>();
        for (HewanVO hewan : hewanList) {
            if (hewan.getHwnNama().toLowerCase().contains(query.toLowerCase()) ||
                    String.valueOf(hewan.getHwnId()).contains(query)) {
                filteredList.add(hewan);
            }
        }
        hewanAdapter.filterList(filteredList);
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_login, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public void onEditHewanClick(HewanVO hewan) {
        EditHewanFragment fragment = EditHewanFragment.newInstance(
                hewan.getHwnId(),
                hewan.getHwnNama(),
                hewan.getHwnUsia(),
                hewan.getHwnBerat(),
                hewan.getHwnMasuk()
        );
        navigateToFragment(fragment);
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