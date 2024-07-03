package com.polytechnic.astra.ac.id.internak.API.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.polytechnic.astra.ac.id.internak.API.ApiUtils;
import com.polytechnic.astra.ac.id.internak.API.Service.UserService;
import com.polytechnic.astra.ac.id.internak.API.VO.ApiResponse;
import com.polytechnic.astra.ac.id.internak.API.VO.UserVO;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private final UserService userService;
    private final MutableLiveData<UserVO> userData = new MutableLiveData<>();

    public UserRepository() {
        this.userService = ApiUtils.getUserService();
    }

    public LiveData<UserVO> getUserData() {
        return userData;
    }

    public void loginUser(String email, String password) {
        userService.login(email, password).enqueue(new Callback<ApiResponse<UserVO>>() {
            @Override
            public void onResponse(Call<ApiResponse<UserVO>> call, Response<ApiResponse<UserVO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse<UserVO> apiResponse = response.body();
                    if (apiResponse.getStatus() == 200 && apiResponse.getData() != null) {
                        Log.d("UserRepository", "Login successful: " + apiResponse.getData().toString());
                        userData.setValue(apiResponse.getData());
                    } else {
                        Log.e("UserRepository", "Login failed: " + apiResponse.getMessage());
                        userData.setValue(null);
                    }
                } else {
                    try {
                        if (response.errorBody() != null) {
                            Log.e("UserRepository", "Login failed: " + response.errorBody().string());
                        } else {
                            Log.e("UserRepository", "Login failed with unknown error");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    userData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<UserVO>> call, Throwable t) {
                Log.e("UserRepository", "Login failed: " + t.getMessage());
                userData.setValue(null);
            }
        });
    }

    public void registerUser(UserVO user) {
        Call<UserVO> call = userService.registerUser(user);
        call.enqueue(new Callback<UserVO>() {
            @Override
            public void onResponse(Call<UserVO> call, Response<UserVO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("UserRepository", "Registration successful: " + response.body().toString());
                    userData.setValue(response.body());
                } else {
                    try {
                        if (response.errorBody() != null) {
                            Log.e("UserRepository", "Registration failed: " + response.errorBody().string());
                        } else {
                            Log.e("UserRepository", "Registration failed with unknown error");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    userData.setValue(null); // handle the failure case
                }
            }

            @Override
            public void onFailure(Call<UserVO> call, Throwable t) {
                Log.e("UserRepository", "Registration failed: " + t.getMessage());
                userData.setValue(null);
            }
        });
    }
}
