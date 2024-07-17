package com.polytechnic.astra.ac.id.internak.API;

import com.polytechnic.astra.ac.id.internak.API.Service.HewanService;
import com.polytechnic.astra.ac.id.internak.API.Service.KandangService;
import com.polytechnic.astra.ac.id.internak.API.Service.NotifikasiService;
import com.polytechnic.astra.ac.id.internak.API.Service.UserService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {
    public static final String API_BASE_URL = "http://10.1.6.174:8080/";

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

    private static Retrofit getClient(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
