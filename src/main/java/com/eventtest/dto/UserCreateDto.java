package com.eventtest.dto;

import com.eventtest.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateDto {

    @NotBlank
    @Length(max = 50)
    private String firstName;
    @Length(max = 50)
    private String lastName;

    @Email(message = "Invalid email address")
    private String email;
    @Length(min = 8, max = 30)
    private String password;

//    private List<Role> roles;
}
