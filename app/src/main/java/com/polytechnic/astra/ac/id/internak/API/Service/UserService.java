package com.polytechnic.astra.ac.id.internak.API.Service;

import com.polytechnic.astra.ac.id.internak.API.VO.ApiResponse;
import com.polytechnic.astra.ac.id.internak.API.VO.UserVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @POST("user/registerUser")
    Call<UserVO> registerUser(@Body UserVO user);

    @GET("user/getUserByEmailAndPassword")
    Call<ApiResponse<UserVO>> login(@Query("email") String email, @Query("password") String password);

    @POST("user/resetPassword")
    Call<ApiResponse<Void>> resetPassword(@Query("oldpassword") String oldPassword, @Query("newpassword") String newPassword);
}
