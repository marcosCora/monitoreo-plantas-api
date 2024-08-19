package com.api.techforb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_users")
    private Long idUser;
    private String name;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns =
                @JoinColumn(name = "user_id", referencedColumnName = "id_users"),
                inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id_roles"))
    private List<Role> roles = new ArrayList<>();

}
