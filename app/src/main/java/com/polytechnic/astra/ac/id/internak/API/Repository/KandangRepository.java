package com.polytechnic.astra.ac.id.internak.API.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.polytechnic.astra.ac.id.internak.API.ApiUtils;
import com.polytechnic.astra.ac.id.internak.API.Service.KandangService;
import com.polytechnic.astra.ac.id.internak.API.VO.ApiResponse;
import com.polytechnic.astra.ac.id.internak.API.VO.KandangVO;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KandangRepository {

    private final KandangService kandangService;
    private final MutableLiveData<List<KandangVO>> kandangListData;

    public KandangRepository() {
        this.kandangService = ApiUtils.getKandangService();
        this.kandangListData = new MutableLiveData<>();
    }

    public LiveData<List<KandangVO>> getKandangListData() {
        return kandangListData;
    }
    public void loadKandangData(int idUser) {
        kandangService.getKandang(idUser).enqueue(new Callback<KandangVO>() {
            @Override
            public void onResponse(Call<KandangVO> call, Response<KandangVO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    kandangListData.setValue(response.body().getData());
                } else {
                    Log.e("KandangRepository", "Gagal memuat data Hewan: " + response.message());
                    kandangListData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<KandangVO> call, Throwable t) {
                Log.e("KandangRepository", "Panggilan API gagal: ", t);
                kandangListData.setValue(null);
            }
        });
    }
    public void createKandang(KandangVO kandang) {
        Log.d("KandangRepository", "Mengirim data ke server: " + kandang.toString());
        kandangService.Create(kandang).enqueue(new Callback<KandangVO>() {
            @Override
            public void onResponse(Call<KandangVO> call, Response<KandangVO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("KandangRepository", "Berhasil membuat Kandang: " + response.body().toString());
                    loadKandangData(kandang.getUsrId());
                } else {
                    try {
                        if (response.errorBody() != null) {
                            Log.e("KandangRepository", "Gagal membuat Kandang: " + response.errorBody().string());
                        } else {
                            Log.e("KandangRepository", "Gagal membuat dengan kesalahan yang tidak diketahui");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<KandangVO> call, Throwable t) {
                Log.e("HewanRepository", "Gagal membuat: " + t.getMessage());
            }
        });
    }

    public void updateKandang(KandangVO kandang) {
        kandangService.updateKandang(kandang).enqueue(new Callback<KandangVO>() {
            @Override
            public void onResponse(Call<KandangVO> call, Response<KandangVO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("KandangRepository", "Berhasil memperbarui Kandang: " + response.body().toString());
                } else {
                    try {
                        if (response.errorBody() != null) {
                            Log.e("KandangRepository", "Gagal memperbarui Kandang: " + response.errorBody().string());
                        } else {
                            Log.e("KandangRepository", "Gagal memperbarui dengan kesalahan yang tidak diketahui");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<KandangVO> call, Throwable t) {
                Log.e("KandangRepository", "Gagal memperbarui: " + t.getMessage());
            }
        });
    }

    public void deleteKandang(Integer idKandang, MutableLiveData<Boolean> deleteResult) {
        kandangService.deleteKandang(idKandang).enqueue(new Callback<ApiResponse<KandangVO>>() {
            @Override
            public void onResponse(Call<ApiResponse<KandangVO>> call, Response<ApiResponse<KandangVO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<KandangVO> apiResponse = response.body();
                    if (apiResponse.getStatus() == 200) {
                        Log.d("KandangRepository", "Berhasil menghapus Kandang: " + apiResponse.getMessage());
                        deleteResult.setValue(true);
                        // Muat ulang data jika perlu
                        loadKandangData(1); // Asumsi memuat data ulang untuk idKandang = 1
                    } else {
                        Log.e("KandangRepository", "Gagal menghapus Kandang: " + apiResponse.getMessage());
                        deleteResult.setValue(false);
                    }
                } else {
                    try {
                        if (response.errorBody() != null) {
                            Log.e("KandangRepository", "Gagal menghapus Kandang: " + response.errorBody().string());
                        } else {
                            Log.e("KandangRepository", "Gagal menghapus dengan kesalahan yang tidak diketahui");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    deleteResult.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<KandangVO>> call, Throwable t) {
                Log.e("KandangRepository", "Gagal menghapus: " + t.getMessage());
                deleteResult.setValue(false);
            }
        });
    }
}
