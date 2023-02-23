package com.eventtest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;

    @Column(nullable = false, unique = true)
    private String password;
//    @ManyToMany
//    @JoinTable(name="user_role",
//            joinColumns = {@JoinColumn(name = "id_user")},
//    inverseJoinColumns = {@JoinColumn(name = "id_role")})
//    private List<Role> roles;


}
