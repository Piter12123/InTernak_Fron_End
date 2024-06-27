package com.polytechnic.astra.ac.id.internak.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.polytechnic.astra.ac.id.internak.API.Repository.UserRepository;
import com.polytechnic.astra.ac.id.internak.API.VO.UserVO;


public class UserViewModel extends ViewModel {
    private final UserRepository userRepository;
    private final LiveData<UserVO> userData;

    public UserViewModel() {
        this.userRepository = new UserRepository();
        this.userData = userRepository.getUserData();
    }

    public LiveData<UserVO> getUserData() {
        return userData;
    }

    public void registerUser(UserVO user) {
        userRepository.registerUser(user);
    }
}