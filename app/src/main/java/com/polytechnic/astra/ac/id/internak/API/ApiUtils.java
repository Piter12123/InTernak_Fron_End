package com.polytechnic.astra.ac.id.internak.API;

import com.polytechnic.astra.ac.id.internak.API.Service.HewanSakitService;
import com.polytechnic.astra.ac.id.internak.API.Service.HewanService;
import com.polytechnic.astra.ac.id.internak.API.Service.KandangService;
import com.polytechnic.astra.ac.id.internak.API.Service.NotifikasiService;
import com.polytechnic.astra.ac.id.internak.API.Service.PerkembanganService;
import com.polytechnic.astra.ac.id.internak.API.Service.UserService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {
    public static final String API_BASE_URL = "http://10.1.11.57:9090/";

    private ApiUtils() {
    }
    public static UserService getUserService() {
        return getClient(API_BASE_URL).create(UserService.class);
    }
    public static HewanService getHewanService() {
        return getClient(API_BASE_URL).create(HewanService.class);
    }

    public static KandangService getKandangService() {
        return getClient(API_BASE_URL).create(KandangService.class);
    }

    public static KandangService getNotifikasiService() {
        return getClient(API_BASE_URL).create(KandangService.class);
    }
    public static HewanSakitService getHewanSakitService() {
        return getClient(API_BASE_URL).create(HewanSakitService.class);
    }
    public static PerkembanganService getPerkembanganService() {
        return getClient(API_BASE_URL).create(PerkembanganService.class);
    }

    private static Retrofit getClient(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
