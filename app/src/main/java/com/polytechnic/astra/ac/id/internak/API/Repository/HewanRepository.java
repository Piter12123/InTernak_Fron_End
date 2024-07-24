package com.polytechnic.astra.ac.id.internak.API.Repository;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.polytechnic.astra.ac.id.internak.API.ApiUtils;
import com.polytechnic.astra.ac.id.internak.API.Service.HewanService;
import com.polytechnic.astra.ac.id.internak.API.VO.ApiResponse;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HewanRepository {
    private final HewanService hewanService;
    private final MutableLiveData<List<HewanVO>> hewanListData;
    private final MutableLiveData<List<Integer>> kandangListData;

    public HewanRepository() {
        this.hewanService = ApiUtils.getHewanService();
        this.hewanListData = new MutableLiveData<>();
        this.kandangListData = new MutableLiveData<>();
    }

    public LiveData<List<HewanVO>> getHewanListData() {
        return hewanListData;
    }

    public LiveData<List<Integer>> getKandangListData() {
        return kandangListData;
    }

    public void loadHewanData(int idUser) {
        hewanService.gethewan(idUser).enqueue(new Callback<HewanVO>() {
            @Override
            public void onResponse(Call<HewanVO> call, Response<HewanVO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    hewanListData.setValue(response.body().getData());
                } else {
                    Log.e("HewanRepository", "Gagal memuat data Hewan: " + response.message());
                    hewanListData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<HewanVO> call, Throwable t) {
                Log.e("HewanRepository", "Panggilan API gagal: ", t);
                hewanListData.setValue(null);
            }
        });
    }

    public void loadKandangData(int userId) {
        hewanService.getKandangByUserId(userId).enqueue(new Callback<ApiResponse<List<Integer>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Integer>>> call, Response<ApiResponse<List<Integer>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    kandangListData.setValue(response.body().getData());
                } else {
                    Log.e("HewanRepository", "Gagal memuat data Kandang: " + response.message());
                    kandangListData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Integer>>> call, Throwable t) {
                Log.e("HewanRepository", "Panggilan API gagal: ", t);
                kandangListData.setValue(null);
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

    public void updateHewan(HewanVO hewan) {
        hewanService.updateHewan(hewan).enqueue(new Callback<HewanVO>() {
            @Override
            public void onResponse(Call<HewanVO> call, Response<HewanVO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("HewanRepository", "Berhasil memperbarui Hewan: " + response.body().toString());
                } else {
                    try {
                        if (response.errorBody() != null) {
                            Log.e("HewanRepository", "Gagal memperbarui Hewan: " + response.errorBody().string());
                        } else {
                            Log.e("HewanRepository", "Gagal memperbarui dengan kesalahan yang tidak diketahui");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<HewanVO> call, Throwable t) {
                Log.e("HewanRepository", "Gagal memperbarui: " + t.getMessage());
            }
        });
    }

    public void deleteHewan(Integer idHewan, MutableLiveData<Boolean> deleteResult) {
        hewanService.deleteHewan(idHewan).enqueue(new Callback<ApiResponse<HewanVO>>() {
            @Override
            public void onResponse(Call<ApiResponse<HewanVO>> call, Response<ApiResponse<HewanVO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<HewanVO> apiResponse = response.body();
                    if (apiResponse.getStatus() == 200) {
                        Log.d("HewanRepository", "Berhasil menghapus Hewan: " + apiResponse.getMessage());
                        deleteResult.setValue(true);
                        loadHewanData(1); // Asumsi memuat data ulang untuk idKandang = 1
                    } else {
                        Log.e("HewanRepository", "Gagal menghapus Hewan: " + apiResponse.getMessage());
                        deleteResult.setValue(false);
                    }
                } else {
                    try {
                        if (response.errorBody() != null) {
                            Log.e("HewanRepository", "Gagal menghapus Hewan: " + response.errorBody().string());
                        } else {
                            Log.e("HewanRepository", "Gagal menghapus dengan kesalahan yang tidak diketahui");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    deleteResult.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<HewanVO>> call, Throwable t) {
                Log.e("HewanRepository", "Gagal menghapus: " + t.getMessage());
                deleteResult.setValue(false);
            }
        });
    }
}
