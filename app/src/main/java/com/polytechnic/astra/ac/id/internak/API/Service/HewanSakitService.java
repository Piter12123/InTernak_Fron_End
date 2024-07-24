package com.polytechnic.astra.ac.id.internak.API.Service;

import com.polytechnic.astra.ac.id.internak.API.VO.ApiResponse;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanSakitVO;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import com.polytechnic.astra.ac.id.internak.API.VO.PerkembanganVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HewanSakitService {
    @GET("monitoring/getMonitoringHewan")
    Call<HewanSakitVO> getHewansakit(@Query("idUser") Integer idUser);

    @POST("monitoring/saveMonitoringHewan")
    Call<HewanSakitVO> Create(@Body HewanSakitVO hewansakit);
    @GET("monitoring/getHewanIdsByUserId")
    Call<ApiResponse<List<Integer>>> getHewanIdsByUserId(@Query("idUser") Integer idUser);

//
//    @GET("monitoring/getHewanIdsByUserId")
//    Call<ApiResponse<List<Integer>>> getHewanIdsByUserId(@Query("idUser") Integer idUser);
}
