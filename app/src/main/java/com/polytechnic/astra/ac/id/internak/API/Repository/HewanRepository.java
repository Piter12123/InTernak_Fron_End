package com.polytechnic.astra.ac.id.internak.API.Repository;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.polytechnic.astra.ac.id.internak.API.ApiUtils;
import com.polytechnic.astra.ac.id.internak.API.Service.HewanService;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HewanRepository {
    private final HewanService hewanService;
    private final MutableLiveData<List<HewanVO>> hewanListData;

    public HewanRepository() {
        this.hewanService = ApiUtils.getHewanService();
        this.hewanListData = new MutableLiveData<>();
    }

    public LiveData<List<HewanVO>> getHewanListData() {
        return hewanListData;
    }

    public void loadHewanData(int idKandang) {
        hewanService.gethewan(idKandang).enqueue(new Callback<HewanVO>() {
            @Override
            public void onResponse(Call<HewanVO> call, Response<HewanVO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    hewanListData.setValue(response.body().getData());
                } else {
                    Log.e("HewanRepository", "Gagal memuat data Hewan: " + response.message());
                    hewanListData.setValue(null); // Mengatur nilai default jika gagal
                }
            }

            @Override
            public void onFailure(Call<HewanVO> call, Throwable t) {
                Log.e("HewanRepository", "Panggilan API gagal: ", t);
                hewanListData.setValue(null); // Mengatur nilai default jika gagal
            }
        });
    }

    public void createHewan(HewanVO hewan) {
        Log.d("HewanRepository", "Mengirim data ke server: " + hewan.toString());
        hewanService.Create(hewan).enqueue(new Callback<HewanVO>() {
            @Override
            public void onResponse(Call<HewanVO> call, Response<HewanVO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("HewanRepository", "Berhasil membuat Hewan: " + response.body().toString());
                    // Opsional, muat ulang data setelah pembuatan
                    loadHewanData(hewan.getKdgId()); // Asumsi memuat data ulang untuk idKandang = 1
                } else {
                    try {
                        if (response.errorBody() != null) {
                            Log.e("HewanRepository", "Gagal membuat Hewan: " + response.errorBody().string());
                        } else {
                            Log.e("HewanRepository", "Gagal membuat dengan kesalahan yang tidak diketahui");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<HewanVO> call, Throwable t) {
                Log.e("HewanRepository", "Gagal membuat: " + t.getMessage());
            }
        });
    }
}
