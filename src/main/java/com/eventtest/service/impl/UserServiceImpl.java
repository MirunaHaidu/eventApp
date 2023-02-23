package com.eventtest.service.impl;

import com.eventtest.service.UserService;
import com.eventtest.convertor.UserConvertor;
import com.eventtest.dto.UserCreateDto;
import com.eventtest.dto.UserInfoDto;

import com.eventtest.model.User;

import com.eventtest.repository.EventRepository;
import com.eventtest.repository.UserRepository;
import com.eventtest.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public UserServiceImpl(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override

    public UserInfoDto registerUser(UserCreateDto userCreateDto) {

        return UserConvertor.entityToInfoDto(userRepository.save(UserConvertor
                .createDtoToEntity(userCreateDto)));
    }

    @Override
    public List<UserInfoDto> getAllUsers() {
        List<UserInfoDto> userInfoDto = new ArrayList<>();
        userRepository.findAll().forEach(user -> userInfoDto.add(UserConvertor
                .entityToInfoDto(user)));


        return userInfoDto;
    }


    @Override
    public User findByEmail(String email)
    {
//        Optional<User> user = userRepository.findByEmail(email);
//        if(user.isPresent())
//        {
//            return user.get();
//        }
//        else {
//            throw new RuntimeException("User not find Exception");
//        }
        return userRepository.findByEmail(email);

    }

//    @Override
//    public void saveUser(User user) {
//        userRepository.save(user);
//    }
}

