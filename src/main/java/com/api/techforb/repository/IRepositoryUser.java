package com.api.techforb.repository;

import com.api.techforb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IRepositoryUser extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM  users  WHERE users.email = :email", nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);
}
