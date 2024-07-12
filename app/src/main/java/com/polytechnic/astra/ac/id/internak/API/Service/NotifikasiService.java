package com.polytechnic.astra.ac.id.internak.API.Service;

import com.polytechnic.astra.ac.id.internak.API.VO.KandangVO;
import com.polytechnic.astra.ac.id.internak.API.VO.NotifikasiVO;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;
import retrofit2.http.Query;

public interface NotifikasiService {
    @GET("notifikasi/getAllNotifikasi")
    Call<List<NotifikasiVO>> getNotifikasi();
}
