package com.eventtest.repository;

import com.eventtest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    // @Query("SELECT u FROM User u WHERE u.email =:email")
    User findByEmail(String email);
   // User findByName(String name);
}
