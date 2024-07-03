package com.polytechnic.astra.ac.id.internak.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.polytechnic.astra.ac.id.internak.API.ApiUtils;
import com.polytechnic.astra.ac.id.internak.API.Service.HewanService;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import com.polytechnic.astra.ac.id.internak.Adapter.HewanAdapter;
import com.polytechnic.astra.ac.id.internak.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HewanFragment extends Fragment {
    private RecyclerView recyclerView;
    private HewanAdapter hewanAdapter;
    private ImageButton add;
    private EditText etSearch;
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
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterHewan(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No action needed
            }
        });

        loadHewanData();

        return view;
    }

    private void loadHewanData() {
        HewanService hewanService = ApiUtils.getHewanService();
        hewanService.gethewan(1).enqueue(new Callback<HewanVO>() {
            @Override
            public void onResponse(Call<HewanVO> call, Response<HewanVO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    HewanVO apiResponse = response.body();
                    hewanList = apiResponse.getData();
                    if (hewanList != null && !hewanList.isEmpty()) {
                        hewanAdapter = new HewanAdapter(hewanList);
                        recyclerView.setAdapter(hewanAdapter);
                    } else {
                        Log.d("HewanFragment", "No Hewan data found.");
                    }
                } else {
                    Log.d("HewanFragment", "Response unsuccessful or empty: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<HewanVO> call, Throwable t) {
                Log.e("HewanFragment", "API call failed: ", t);
            }
        });
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
}
