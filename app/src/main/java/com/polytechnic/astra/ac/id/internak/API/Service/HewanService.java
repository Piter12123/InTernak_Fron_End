package com.polytechnic.astra.ac.id.internak.API.Service;

import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HewanService {
    @POST("hewan/saveHewanKandang")
    Call<HewanVO> Create(@Body HewanVO hewan);
}
