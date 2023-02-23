package com.eventtest.controller;


import com.eventtest.dto.UserCreateDto;
import com.eventtest.dto.UserInfoDto;
import com.eventtest.model.User;
import com.eventtest.repository.UserRepository;
import com.eventtest.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

//    @PostMapping("/register")
//    public ResponseEntity<UserInfoDto> registerUser(@RequestBody @Valid UserCreateDto userCreateDto){
//       return ResponseEntity.ok(userService.registerUser(userCreateDto));
//    }

    @GetMapping("index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserCreateDto user = new UserCreateDto();
        model.addAttribute("user", user);
        return "register";
    }



    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserInfoDto userInfoDto,
                               BindingResult result,
                               Model model){
//        Optional<User> existing = userRepository.findByEmail(userInfoDto.getEmail());
        User existing = userService.findByEmail(userInfoDto.getEmail());
        if (existing !=null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", userInfoDto);
            return "register";
        }
        userService.saveUser(userInfoDto);
        return "redirect:/register/success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserInfoDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }



}
