package com.eventtest.service;

import com.eventtest.dto.UserCreateDto;
import com.eventtest.dto.UserInfoDto;

import com.eventtest.model.User;


import java.util.List;



public interface UserService {


    UserInfoDto registerUser(UserCreateDto userCreateDto);
    List<UserInfoDto> getAllUsers();
//    void saveUser(User user);

    User findByEmail(String email);

}

