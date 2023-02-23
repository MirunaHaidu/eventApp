package com.eventtest.convertor;

import com.eventtest.dto.UserCreateDto;
import com.eventtest.dto.UserInfoDto;
import com.eventtest.model.User;

public class UserConvertor {

    public static User createDtoToEntity(UserCreateDto userCreateDto){
        User user = new User();
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(userCreateDto.getPassword());
//        user.setRoles(userCreateDto.getRoles());

        return user;

    }

    public static UserInfoDto entityToInfoDto(User user){
        UserInfoDto userInfoDto = new UserInfoDto();

        userInfoDto.setFirstName(user.getFirstName());
        userInfoDto.setLastName(user.getLastName());
        userInfoDto.setEmail(user.getEmail());
        userInfoDto.setPassword(user.getPassword());
//        userInfoDto.setRoles(user.getRoles());

        return userInfoDto;
    }
}
