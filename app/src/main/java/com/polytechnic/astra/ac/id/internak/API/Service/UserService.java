package com.polytechnic.astra.ac.id.internak.API.Service;

import com.polytechnic.astra.ac.id.internak.API.VO.UserVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @POST("user/registerUser")
    Call<UserVO> registerUser(@Body UserVO user);
    }
