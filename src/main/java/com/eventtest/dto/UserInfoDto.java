package com.eventtest.dto;

import com.eventtest.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfoDto {
    private Integer id;

    private String firstName;
    private String lastName;

    private String email;

    private String password;

    private List<Role> roles;

}
