package com.polytechnic.astra.ac.id.internak.API.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.polytechnic.astra.ac.id.internak.API.ApiUtils;
import com.polytechnic.astra.ac.id.internak.API.Service.NotifikasiService;
import com.polytechnic.astra.ac.id.internak.API.VO.NotifikasiVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotifikasiRepository {

    private final NotifikasiService notifikasiService;

    public NotifikasiRepository() {
        notifikasiService = ApiUtils.getNotifikasiService();
    }

    public LiveData<List<NotifikasiVO>> getNotifikasiList() {
        MutableLiveData<List<NotifikasiVO>> data = new MutableLiveData<>();
        notifikasiService.getNotifikasi().enqueue(new Callback<List<NotifikasiVO>>() {
            @Override
            public void onResponse(Call<List<NotifikasiVO>> call, Response<List<NotifikasiVO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    data.setValue(response.body());
                    Log.d("NotifikasiRepository", "Data received: " + response.body().size() + " items");
                } else {
                    data.setValue(null);
                    Log.e("NotifikasiRepository", "Response unsuccessful or empty");
                    Log.e("NotifikasiRepository", "Response code: " + response.code());
                    Log.e("NotifikasiRepository", "Response message: " + response.message());
                    try {
                        Log.e("NotifikasiRepository", "Response error body: " + response.errorBody().string());
                    } catch (Exception e) {
                        Log.e("NotifikasiRepository", "Error reading error body", e);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<NotifikasiVO>> call, Throwable t) {
                data.setValue(null);
                Log.e("NotifikasiRepository", "Failed to fetch data", t);
            }
        });
        return data;
    }
}
