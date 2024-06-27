package com.polytechnic.astra.ac.id.internak.API.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.polytechnic.astra.ac.id.internak.API.ApiUtils;
import com.polytechnic.astra.ac.id.internak.API.Service.HewanService;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HewanRepository {
    private final HewanService hewanService;
    private final MutableLiveData<HewanVO> hewanData = new MutableLiveData<>();

    public HewanRepository() {
        this.hewanService = ApiUtils.getHewanService();
    }

    public LiveData<HewanVO> getHewanData() {
        return hewanData;
    }

    public void createHewan(HewanVO hewan) {
        Log.d("HewanRepository", "Sending data to server: " + hewan.toString());
        Call<HewanVO> call = hewanService.Create(hewan);
        call.enqueue(new Callback<HewanVO>() {
            @Override
            public void onResponse(Call<HewanVO> call, Response<HewanVO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("HewanRepository", "Berhasil Menambahkan Data Hewan: " + response.body().toString());
                    hewanData.setValue(response.body());
                } else {
                    try {
                        if (response.errorBody() != null) {
                            Log.e("HewanRepository", "Gagal Menambahkan Data Hewan: " + response.errorBody().string());
                        } else {
                            Log.e("HewanRepository", "Create failed with unknown error");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    hewanData.setValue(null); // handle the failure case
                }
            }

            @Override
            public void onFailure(Call<HewanVO> call, Throwable t) {
                Log.e("HewanRepository", "Create failed: " + t.getMessage());
                hewanData.setValue(null); // handle the failure case
            }
        });
    }
}
