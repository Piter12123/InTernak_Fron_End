package com.polytechnic.astra.ac.id.internak.API.Service;

import com.polytechnic.astra.ac.id.internak.API.VO.HewanSakitVO;
import com.polytechnic.astra.ac.id.internak.API.VO.PerkembanganVO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PerkembanganService {
    @GET("perkembangan/getPerkembanganById")
    Call<PerkembanganVO> getperkembanganByTmo(@Query("idPerkembangan") Integer idPerkembangan);
}
