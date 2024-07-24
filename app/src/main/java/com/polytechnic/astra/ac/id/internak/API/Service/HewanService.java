package com.polytechnic.astra.ac.id.internak.API.Service;

import com.polytechnic.astra.ac.id.internak.API.VO.ApiResponse;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import com.polytechnic.astra.ac.id.internak.API.VO.KandangVO;
import com.polytechnic.astra.ac.id.internak.API.VO.UserVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface HewanService {
    @POST("hewan/saveHewanKandang")
    Call<HewanVO> Create(@Body HewanVO hewan);
    @GET("hewan/getHewanKandang")
    Call<HewanVO> gethewan(@Query("idUser") Integer idUser);
    @POST("hewan/updateHewanKandang")
    Call<HewanVO> updateHewan(@Body HewanVO hewan);
    @POST("hewan/deleteHewan")
    Call<ApiResponse<HewanVO>> deleteHewan(@Query("idHewan") int idHewan);

    @GET("hewan/getKandangByUserId")
    Call<ApiResponse<List<Integer>>> getKandangByUserId(@Query("userId") Integer userId);

}