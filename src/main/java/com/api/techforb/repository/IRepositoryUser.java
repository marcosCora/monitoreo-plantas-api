package com.api.techforb.repository;

import com.api.techforb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryUser extends JpaRepository<User, Long> {
}
