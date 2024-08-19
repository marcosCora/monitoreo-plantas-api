package com.api.techforb.repository;

import com.api.techforb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRepositoryUser extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean existByEmail(String email);

}
