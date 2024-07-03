package com.polytechnic.astra.ac.id.internak.API.Service;

import com.polytechnic.astra.ac.id.internak.API.VO.ApiResponse;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import com.polytechnic.astra.ac.id.internak.API.VO.KandangVO;
import com.polytechnic.astra.ac.id.internak.API.VO.UserVO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HewanService {
    @POST("hewan/saveHewanKandang")
    Call<HewanVO> Create(@Body HewanVO hewan);
    @GET("hewan/getHewanKandang")
    Call<HewanVO> gethewan(@Query("idKandang") Integer idKandang);

}
