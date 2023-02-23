package com.eventtest.config;

import com.eventtest.model.User;
import com.eventtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;


    @Autowired

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

       Optional<User> user = userRepository.findByEmail(email);
       if(user.isPresent()){
           return new CustomUserDetails(user.get());
        }else {
           throw new UsernameNotFoundException("User not found");
        }

    }
}
