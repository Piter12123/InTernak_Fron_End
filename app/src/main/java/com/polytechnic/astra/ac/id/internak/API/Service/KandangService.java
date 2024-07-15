package com.polytechnic.astra.ac.id.internak.API.Service;

import com.polytechnic.astra.ac.id.internak.API.VO.ApiResponse;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import com.polytechnic.astra.ac.id.internak.API.VO.KandangVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface KandangService extends NotifikasiService {
    @POST("kandang/saveKandangUser")
    Call<KandangVO> Create(@Body KandangVO kandang);
    @GET("kandang/getKandangUser")
    Call<KandangVO> getKandang(@Query("idUser") Integer idUser);
    @POST("kandang/updateKandangUser")
    Call<KandangVO> updateKandang(@Body KandangVO kandang);
}
