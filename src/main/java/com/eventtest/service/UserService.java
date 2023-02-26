package com.eventtest.service;

import com.eventtest.dto.UserDto;

import com.eventtest.model.User;


import java.util.List;



public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}

