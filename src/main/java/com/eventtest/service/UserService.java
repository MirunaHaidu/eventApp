package com.eventtest.service;

import com.eventtest.dto.UserCreateDto;
import com.eventtest.dto.UserInfoDto;

import com.eventtest.model.User;


import java.util.List;



public interface UserService {


    UserInfoDto registerUser(UserCreateDto userCreateDto);
    void saveUser(UserInfoDto userInfoDto);


    User findByEmail(String email);

    List<UserInfoDto> findAllUsers();


}

