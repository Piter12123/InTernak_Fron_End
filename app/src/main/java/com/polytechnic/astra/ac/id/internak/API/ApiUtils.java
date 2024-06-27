package com.polytechnic.astra.ac.id.internak.API;

import com.polytechnic.astra.ac.id.internak.API.Service.UserService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {
    public static final String API_BASE_URL = "http://192.168.1.8:9090/";

    private ApiUtils() {
    }

    public static UserService getUserService() {
        return getClient(API_BASE_URL).create(UserService.class);
    }

    private static Retrofit getClient(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
