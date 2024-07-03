package com.polytechnic.astra.ac.id.internak.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.polytechnic.astra.ac.id.internak.API.ApiUtils;
import com.polytechnic.astra.ac.id.internak.API.Service.KandangService;
import com.polytechnic.astra.ac.id.internak.API.VO.KandangVO;
import com.polytechnic.astra.ac.id.internak.Adapter.KandangAdapter;
import com.polytechnic.astra.ac.id.internak.R;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KandangFragment extends Fragment {

    private RecyclerView recyclerView;
    private KandangAdapter kandangAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kandang, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadKandangData();

        return view;
    }
    private void loadKandangData() {
        KandangService kandangService = ApiUtils.getKandangService();
        kandangService.getKandang(2).enqueue(new Callback<KandangVO>() {
            @Override
            public void onResponse(Call<KandangVO> call, Response<KandangVO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    KandangVO apiResponse = response.body();
                    List<KandangVO> kandangList = apiResponse.getData();
                    if (kandangList != null && !kandangList.isEmpty()) {
                        kandangAdapter = new KandangAdapter(kandangList);
                        recyclerView.setAdapter(kandangAdapter);
                    } else {
                        Log.d("KandangFragment", "No kandang data found.");
                    }
                } else {
                    Log.d("KandangFragment", "Response unsuccessful or empty: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<KandangVO> call, Throwable t) {
                Log.e("KandangFragment", "API call failed: ", t);
            }
        });
    }
}
