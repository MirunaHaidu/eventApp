package com.eventtest.service.impl;

import com.eventtest.model.Role;
import com.eventtest.repository.RoleRepository;
import com.eventtest.service.UserService;
import com.eventtest.convertor.UserConvertor;
import com.eventtest.dto.UserCreateDto;
import com.eventtest.dto.UserInfoDto;

import com.eventtest.model.User;

import com.eventtest.repository.EventRepository;
import com.eventtest.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service

public class UserServiceImpl implements UserService {
    private final RoleRepository roleRepository;

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository, EventRepository eventRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override

    public UserInfoDto registerUser(UserCreateDto userCreateDto) {
        return UserConvertor.entityToInfoDto(userRepository.save(UserConvertor
                .createDtoToEntity(userCreateDto)));
    }

    @Override
    public List<UserInfoDto> findAllUsers() {
//        List<UserInfoDto> userInfoDto = new ArrayList<>();
//        userRepository.findAll().forEach(user -> userInfoDto.add(UserConvertor
//                .entityToInfoDto(user)));
//        return userInfoDto;
        return this.userRepository.findAll().stream()
                .map(UserConvertor::entityToInfoDto)
                .collect(Collectors.toList());
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

    @Override
    public void saveUser(UserInfoDto userInfoDto) {
        User user = new User();
        user.setFirstName(userInfoDto.getFirstName());
        user.setLastName(userInfoDto.getLastName());
        user.setEmail(userInfoDto.getEmail());
        user.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
        Role role = roleRepository.findByName("USER");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(List.of(role));
        userRepository.save(user);

    }

//    @Override
//    public void saveUser(User user) {
//        userRepository.save(user);
//    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}

