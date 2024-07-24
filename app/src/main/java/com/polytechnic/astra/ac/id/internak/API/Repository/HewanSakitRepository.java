package com.polytechnic.astra.ac.id.internak.API.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.polytechnic.astra.ac.id.internak.API.ApiUtils;
import com.polytechnic.astra.ac.id.internak.API.Service.HewanSakitService;
import com.polytechnic.astra.ac.id.internak.API.Service.PerkembanganService;
import com.polytechnic.astra.ac.id.internak.API.VO.ApiResponse;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanSakitVO;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import com.polytechnic.astra.ac.id.internak.API.VO.PerkembanganVO;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HewanSakitRepository {
    private final HewanSakitService hewansakitService;
    private final MutableLiveData<List<HewanSakitVO>> hewansakitListData;

    public HewanSakitRepository() {
        this.hewansakitService = ApiUtils.getHewanSakitService();
        this.hewansakitListData = new MutableLiveData<>();
    }

    public LiveData<List<HewanSakitVO>> getHewansakitListData() {
        return hewansakitListData;
    }

    public void loadHewanData(int idUser) {
        hewansakitService.getHewansakit(idUser).enqueue(new Callback<HewanSakitVO>() {
            @Override
            public void onResponse(Call<HewanSakitVO> call, Response<HewanSakitVO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    hewansakitListData.setValue(response.body().getData());
                } else {
                    Log.e("HewanRepository", "Gagal memuat data Hewan: " + response.message());
                    hewansakitListData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<HewanSakitVO> call, Throwable t) {
                Log.e("HewanRepository", "Panggilan API gagal: ", t);
                hewansakitListData.setValue(null);
            }
        });
    }
    public void createHewan(HewanSakitVO hewansakit) {
        Log.d("HewanRepository", "Sending data to server: " + hewansakit.toString());
        hewansakitService.Create(hewansakit).enqueue(new Callback<HewanSakitVO>() {
            @Override
            public void onResponse(Call<HewanSakitVO> call, Response<HewanSakitVO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("HewanRepository", "Successfully created Hewan: " + response.body().toString());
                    loadHewanData(hewansakit.getUsrId());
                } else {
                    try {
                        if (response.errorBody() != null) {
                            Log.e("HewanRepository", "Failed to create Hewan: " + response.errorBody().string());
                        } else {
                            Log.e("HewanRepository", "Unknown error while creating Hewan");
                        }
                    } catch (IOException e) {
                        Log.e("HewanRepository", "IOException: ", e);
                    }
                }
            }

            @Override
            public void onFailure(Call<HewanSakitVO> call, Throwable t) {
                Log.e("HewanRepository", "Failed to create Hewan: ", t);
            }
        });
    }
    public LiveData<List<Integer>> getHewanIdsByUserId(int idUser) {
        MutableLiveData<List<Integer>> hewanIdsLiveData = new MutableLiveData<>();
        hewansakitService.getHewanIdsByUserId(idUser).enqueue(new Callback<ApiResponse<List<Integer>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Integer>>> call, Response<ApiResponse<List<Integer>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    hewanIdsLiveData.setValue(response.body().getData());
                } else {
                    hewanIdsLiveData.setValue(null);
                    Log.e("HewanRepository", "Failed to fetch Hewan IDs: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Integer>>> call, Throwable t) {
                hewanIdsLiveData.setValue(null);
                Log.e("HewanRepository", "API call failed: ", t);
            }
        });
        return hewanIdsLiveData;
    }

}
