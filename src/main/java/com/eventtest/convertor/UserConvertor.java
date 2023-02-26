package com.eventtest.convertor;

import com.eventtest.dto.UserDto;
import com.eventtest.model.User;

public class UserConvertor {
    public static UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        String[] name = user.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setId(user.getId());
        return userDto;
    }
}
