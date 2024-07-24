package com.polytechnic.astra.ac.id.internak.API.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.polytechnic.astra.ac.id.internak.API.ApiUtils;
import com.polytechnic.astra.ac.id.internak.API.Service.HewanSakitService;
import com.polytechnic.astra.ac.id.internak.API.Service.PerkembanganService;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanSakitVO;
import com.polytechnic.astra.ac.id.internak.API.VO.PerkembanganVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerkembanganRepository {
    private final PerkembanganService perkembanganService;
    private final MutableLiveData<List<PerkembanganVO>> perkembanganListData;

    public PerkembanganRepository() {
        this.perkembanganService = ApiUtils.getPerkembanganService();
        this.perkembanganListData = new MutableLiveData<>();
    }

    public LiveData<List<PerkembanganVO>> getPerkembanganListData() {
        return perkembanganListData;
    }

    public void loadHewanData(int idTmo) {
        perkembanganService.getperkembanganByTmo(idTmo).enqueue(new Callback<PerkembanganVO>() {
            @Override
            public void onResponse(Call<PerkembanganVO> call, Response<PerkembanganVO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    perkembanganListData.setValue(response.body().getData());
                } else {
                    Log.e("HewanRepository", "Gagal memuat data Hewan: " + response.message());
                    perkembanganListData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<PerkembanganVO> call, Throwable t) {
                Log.e("HewanRepository", "Panggilan API gagal: ", t);
                perkembanganListData.setValue(null);
            }
        });
    }
}
